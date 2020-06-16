package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.common.DBManager;
import com.sqlserver.ssar.common.GetDictionary;
import com.sqlserver.ssar.model.dao.*;
import com.sqlserver.ssar.model.entity.*;
import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.service.ScoreService;
import com.sqlserver.ssar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ClassUserDao classUserDao;

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private PracticeClassDao practiceClassDao;

    @Override
    @Transactional
    public List<Map> getScoreList(User teacher) {
        List<User> students = userService.getStudentsByTeacher(teacher);
        List<Class> classes = new ArrayList<>();
        List<ClassUser> classUsers = classUserDao.findAllByUser(teacher);
        for (ClassUser classUser : classUsers) {
            classes.add(classUser.getClazz());
        }
        return getScoresByClassesAndTeacher(classes, teacher, students);
    }

    @Override
    public List<Map> getScoreListByTeacherAndClassId(User teacher, Long id) {
        List<User> students = userService.getStudentsByClassId(id);
        List<Class> classes = new ArrayList<>();
        classes.add(classDao.findByIdAndEnable(id, true));
        return this.getScoresByClassesAndTeacher(classes, teacher, students);
    }

    @Override
    public List<Practice> getScoreListByStudent(User student) {

        List<Practice> practices = new ArrayList<>();
        for (ClassUser classUser : classUserDao.findAllByUser(student)) {
            for (PracticeClass practiceClass : practiceClassDao.findAllByClazz(classUser.getClazz())) {
                practices.add(practiceClass.getPractice());
            }
        }
        return practices;
    }

    /**
     * 学生提交自动评阅
     * @param score
     */
    @Override
    @Transactional(readOnly = false)
    public void save(Score score) throws SQLException {
        TrainingLibrary trainingLibrary = score.getTrainingLibrary();
        List<Dictionary> dictionary1 = new ArrayList<>();
        List<Dictionary> dictionary2 = new ArrayList<>();

        String tips = "";
        double num_score = 0;

        DBManager dbManager = new DBManager();
        int row = -1, row1 = -1, row2 = -1;
        Connection conn = dbManager.getConnection();

        switch (trainingLibrary.getType()) {
            case CREATE:
                try {
                    row = dbManager.getSta().executeUpdate(score.getAnswer());
                    conn.commit();//提交JDBC事务，如果没问题，这时才真正的删除了;
                    conn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
                    dictionary1 = GetDictionary.getDictionary();
                    conn.rollback();
                    dictionary2 = trainingLibrary.getDictionaries();

                } catch (Exception e) {
                    conn.rollback();
                    e.printStackTrace();
                } finally {
                    conn.rollback();
                    dbManager.close();
                }
                for (Dictionary dictionary : dictionary2) {
                    if (dictionary1.contains(dictionary)) {
                        num_score += 1;
                    } else {
                        tips += ("表" + dictionary.getTableName() + "中的字段" + dictionary.getColumnName() + "未满足要求；");
                    }
                }
                if (num_score > trainingLibrary.getTotalScore()){
                    num_score = trainingLibrary.getTotalScore();
                }
                break;
            case SELECT:

                try {
                    ResultSet resultSet1 = dbManager.getSta().executeQuery(score.getAnswer());
                    ResultSet resultSet2 = dbManager.getSta().executeQuery(trainingLibrary.getReferenceAnswer());
                    while (resultSet1.next()){
                        while (resultSet2.next()){
                            if (!resultSet1.equals(resultSet2)){
                                tips = "查询结果错误！请修改答案";
                            }
                        }
                    }
                    if (tips == ""){
                        num_score = trainingLibrary.getTotalScore();
                    }
                } catch (Exception e) {
                    conn.rollback();
                    tips = "查询结果错误！请修改答案";
                    e.printStackTrace();
                } finally {
                    conn.rollback();
                    dbManager.close();
                }

                break;
            default:
                try {
                    row1 = dbManager.getSta().executeUpdate(score.getAnswer());
                    conn.commit();
                    conn.setAutoCommit(true);
                    conn.rollback();
                    row2 = dbManager.getSta().executeUpdate(trainingLibrary.getReferenceAnswer());
                    conn.commit();
                    conn.setAutoCommit(true);
                    conn.rollback();
                } catch (Exception e) {
                    conn.rollback();
                    tips = "回答错误：请修改答案！";
                    e.printStackTrace();
                } finally {
                    conn.rollback();
                    dbManager.close();
                }
                if (row1 == row2 && row1 != -1 && row2 != -1) {
                    num_score = trainingLibrary.getTotalScore();
                }
                else {
                    tips = "回答错误：请修改答案！";
                }
        }
        score.setScore(num_score);
        score.setTips(tips);
    }

    /**
     * 通过班级、教师、学生获取成绩列表
     *      * @param classes
     *      * @param teacher
     *      * @param students
     * @return
     */
    public List<Map> getScoresByClassesAndTeacher(List<Class> classes, User teacher, List<User> students){

        List<Practice> practices = new ArrayList<>();
        for (Class aClass : classes) {
            for (PracticeClass practiceClass : practiceClassDao.findAllByClazz(aClass)) {
                practices.add(practiceClass.getPractice());
            }
        }

        List<Map> scoreMapList = new ArrayList<>();// 学生成绩封装

        Map<String, Object> headerMap = new HashMap<>();    // 学生成绩列表头信息
        headerMap.put("stuName", "姓名");
        headerMap.put("stuNumber", "学号");

        for (int i = 0; i < students.size(); i++) {//学生成绩按照practice和student进行封装
            if (i == 0) {
                headerMap.put("pricticeSise", practices.size());// 成绩条数
            }
            User student = students.get(i);

            Map<String, Object> lineMap = new HashMap<>();  // 学生成绩列表行信息
            lineMap.put("stuName", student.getUserName()); // 添加学生信息
            lineMap.put("stuNumber", student.getAccount());
            Iterator iterator = practices.iterator();
            int i1 = 0;
            while (iterator.hasNext()){
                Practice practice = (Practice)iterator.next();
                if(i == 0 && i1 == 0) {
                    headerMap.put("prictice" + i1, practice.getPracticeName());    //单次作业名称
                }
                Set<Score> scores = scoreDao.findALlByPracticeAndStudent(practice, student);
                double score = 0;   // 单次作业总成绩
                for (Score aScore : scores) {
                    score += aScore.getScore();
                }
                lineMap.put("prictice" + i1, score); //行单次作业总成绩
                i1++;
            }
            scoreMapList.add(lineMap);
        }
        scoreMapList.add(0,headerMap);
        return scoreMapList;
    }
}
