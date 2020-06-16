package com.sqlserver.ssar.controller.student;

import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.PracticeService;
import com.sqlserver.ssar.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student/practice")
@Slf4j
public class StuPracticeController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private PracticeService practiceService;

    @RequestMapping
    public void index(){}

    @RequestMapping("/list")
    public List<Practice> stuPracticeList(HttpSession session){
        User student = (User)session.getAttribute("user");
        return practiceService.findAllByTeacher(student);
    }

    /**
     * 已完成作业
     * @param session
     * @return
     */
    @RequestMapping("/list/done")
    public List<Practice> stuPracticeListDone(HttpSession session){
        User student = (User)session.getAttribute("user");
        return practiceService.findAllByTeacherAndDone(student, true);
    }

    /**
     * 未完成作业
     * @param session
     * @return
     */
    @RequestMapping("/list/undone")
    public List<Practice> stuPracticeListUndone(HttpSession session){
        User student = (User)session.getAttribute("user");
        return practiceService.findAllByTeacherAndDone(student, false);
    }

    /**
     * 作业详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/info")
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            Practice practice = practiceService.findById(id);
            model.addAttribute("practice", practice);
        }
        return "student/practice/info";
    }

    @RequestMapping("/training/list")
    public String trainingListByPractice(@PathVariable("id") Long id, Model model){
        Set<TrainingLibrary> trainingLibrarys = trainingService.getTrainingLibraryByPracticeId(id);
        model.addAttribute("trainingLibrarys", trainingLibrarys);
        return "student/practice/training/list";
    }

}
