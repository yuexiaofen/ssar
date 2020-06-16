package com.sqlserver.ssar.controller.system;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.dao.GradeDao;
import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.Grade;
import com.sqlserver.ssar.service.ClassService;
import com.sqlserver.ssar.service.GradeService;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/system/cls")
@Slf4j
@Transactional(readOnly = true)
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private GradeService gradeService;

    @RequestMapping
    public void index(){}

    @RequestMapping({"/form","/load"})
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            Class cls = classService.findById(id);
            model.addAttribute("cls",cls);
        }
        Iterable<Grade> grades = gradeService.findAll();
        model.addAttribute("grades", gradeService.findAll());
        return "system/cls/form";
    }

    @PostMapping("/list")
    @ResponseBody
    public Iterable<Class> list(){
        return classService.findAll();
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(@Valid Class cls, BindingResult br){
        if(!br.hasErrors()){
            classService.save(cls);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id){
        Class cls =classService.findById(id);
        if (cls != null){
            classService.delete(cls);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }
}
