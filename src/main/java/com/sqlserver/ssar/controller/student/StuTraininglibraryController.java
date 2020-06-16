package com.sqlserver.ssar.controller.student;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student/training")
@Slf4j
public class StuTraininglibraryController {

    @Autowired
    private TrainingService trainingService;

    @RequestMapping
    public void index(){}

    @RequestMapping("/list")
    @ResponseBody
    public List<TrainingLibrary> trainingList(){
        return trainingService.findAllByEnable(true);
    }

    @RequestMapping("/info")
    public String trainingLibraryInfo(@PathVariable("id") Long id, Model model){
        TrainingLibrary trainingLibrary = trainingService.getTrainingLibraryById(id);
        model.addAttribute("trainingLibrary", trainingLibrary);
        return "/student/training/info";
    }




}
