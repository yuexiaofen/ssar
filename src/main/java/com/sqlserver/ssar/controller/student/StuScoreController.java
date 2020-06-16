package com.sqlserver.ssar.controller.student;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.dao.ClassDao;
import com.sqlserver.ssar.model.dao.PracticeDao;
import com.sqlserver.ssar.model.dao.ScoreDao;
import com.sqlserver.ssar.model.entity.*;
import com.sqlserver.ssar.service.ClassService;
import com.sqlserver.ssar.service.PracticeService;
import com.sqlserver.ssar.service.ScoreService;
import com.sqlserver.ssar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student/score")
@Slf4j
public class StuScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Practice> scoreList(HttpSession session){
            User student = (User) session.getAttribute("user");
            return scoreService.getScoreListByStudent(student);
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(Score score, BindingResult br){
        if(!br.hasErrors()){
            try {
                scoreService.save(score);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

}
