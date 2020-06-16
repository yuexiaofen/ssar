package com.sqlserver.ssar.controller.teacher;
;
import com.sqlserver.ssar.model.entity.*;
import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.service.ClassService;
import com.sqlserver.ssar.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/teacher/score")
@Slf4j
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ClassService classService;

    @RequestMapping("/list")
    @ResponseBody
    public  List<Map> scoreList(HttpSession session){
            User teacher = (User) session.getAttribute("user");
            return scoreService.getScoreList(teacher);
        }

    @RequestMapping("/class/list")
    @ResponseBody
    public List<Class> classList(HttpSession session){
        User teacher = (User) session.getAttribute("user");
    return classService.findAllByTeacher(teacher);
    }

    @RequestMapping("/class/scorelist")
    @ResponseBody
    public List<Map> classScoreList(HttpSession session, Long id){
        User teacher = (User) session.getAttribute("user");
        return scoreService.getScoreListByTeacherAndClassId(teacher, id);
    }
}
