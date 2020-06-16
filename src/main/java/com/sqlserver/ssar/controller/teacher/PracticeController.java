package com.sqlserver.ssar.controller.teacher;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.PracticeService;
import com.sqlserver.ssar.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher/practice")
@Slf4j
public class PracticeController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private PracticeService practiceService;

    @RequestMapping
    public void index(){}

    @RequestMapping("/list")
    @ResponseBody
    public List<Practice> teacherPracticeList(HttpSession session){
        User teacher = (User)session.getAttribute("user");
        List<Practice> practices = practiceService.findAllByTeacher(teacher);
        System.out.println(practices);
        return practices;
    }

    @RequestMapping({"/form","/load"})
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            Practice practice = practiceService.findById(id);
            model.addAttribute("practice", practice);
        }
        return "teacher/practice/form";
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(@Valid Practice practice, BindingResult br, Long... ids){
        if(!br.hasErrors()){
            practiceService.save(practice, ids);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional(readOnly = false)
    public JsonResult delete(Long id){
        return practiceService.delete(id);
    }
}
