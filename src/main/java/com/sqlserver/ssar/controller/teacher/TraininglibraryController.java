package com.sqlserver.ssar.controller.teacher;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.dao.TrainingLibraryDao;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.ClassService;
import com.sqlserver.ssar.service.TrainingService;
import com.sqlserver.ssar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher/training")
@Slf4j
public class TraininglibraryController {

    @Autowired
    private TrainingService trainingService;

    @RequestMapping
    public void index(){}

    @RequestMapping("/list")
    @ResponseBody
    public List<TrainingLibrary> traingList(){
        return trainingService.findAllByEnable(true);
    }

    @RequestMapping({"/form","/load"})
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            TrainingLibrary training = trainingService.findById(id);
            model.addAttribute("training",training);
        }
        return "teacher/training/form";
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(@Valid TrainingLibrary training, @Valid Dictionary dictionary, BindingResult br){
        if(!br.hasErrors()){
            trainingService.save(training, dictionary);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id){
        return trainingService.delete(id);
    }

}
