package com.sqlserver.ssar.controller.system;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.dao.PermissionDao;
import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/system/permission")
@Slf4j
@Transactional(readOnly = true)
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping
    public void index(){}

    @RequestMapping({"/form","/load"})
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            Permission permission = permissionService.findById(id);
            model.addAttribute("permission",permission);
        }
        return "system/permission/form";
    }

    @PostMapping("/list")
    @ResponseBody
    public List<Permission> list(){
        return permissionService.findAllByParentIsNull();
    }

    @PostMapping("/combo")
    @ResponseBody
    public List<Permission> combo(){
        return permissionService.findAllByParentIsNull();
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(@Valid Permission permission, BindingResult br){
        if(!br.hasErrors()){
            permissionService.save(permission);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id){
        Permission permission = permissionService.findById(id);
        if (permission != null){
            permissionService.delete(permission);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }
}
