package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.dao.PracticeDao;
import com.sqlserver.ssar.model.dao.TrainingLibraryDao;
import com.sqlserver.ssar.model.dao.TrainingLibraryPracticeDao;
import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.model.entity.TrainingLibraryPractice;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PracticeServiceImpl implements PracticeService {

    @Autowired
    private PracticeDao practiceDao;

    @Autowired
    private TrainingLibraryDao trainingLibraryDao;

    @Autowired
    private TrainingLibraryPracticeDao trainingLibraryPracticeDao;

    /**
     * 查找所有有效的作业
     * @param teacher
     * @return
     */
    @Override
    public List<Practice> findAllByTeacher(User teacher) {
        return practiceDao.findAllByEnableAndTeacher(true, teacher);
    }

    /**
     * 是/否已完成作业
     * @param student
     * @param b
     * @return
     */
    @Override
    public List<Practice> findAllByTeacherAndDone(User student, boolean b) {
        return practiceDao.findAllByEnableAndTeacherAndDone(true, student, b);
    }

    @Override
    public Practice findById(Long id) {
        return practiceDao.findById(id).get();
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Practice practice, Long... ids) {

        for (Long id : ids) {
            TrainingLibraryPractice trainingLibraryPractice = new TrainingLibraryPractice();
            trainingLibraryPractice.setPractice(practice);
            trainingLibraryPractice.setTrainingLibrary(trainingLibraryDao.findById(id).get());
            List<TrainingLibraryPractice> libraryPractices = trainingLibraryPracticeDao.findAllByPractice(practice);
            libraryPractices.add(trainingLibraryPractice);
        }
        practiceDao.save(practice);
    }

    @Override
    @Transactional(readOnly = false)
    public JsonResult delete(Long id) {
        Practice practice = practiceDao.findById(id).get();
        if (practice != null){
            practiceDao.deleteById(id);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }
}
