package com.sqlserver.ssar.controller.system;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.service.RoleService;
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
@RequestMapping("/system/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping
    public void index(){}

    @RequestMapping("/list")
    @ResponseBody
    public Iterable list(){
        return roleService.findAll();
    }

    @RequestMapping({"/form","/load"})
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            Role role = roleService.findById(id);
            model.addAttribute("role",role);
        }
        return "system/role/form";
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(@Valid Role role, BindingResult br){
        if(!br.hasErrors()){
            roleService.save(role);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id){
        Role role = roleService.findById(id);
        if (role != null){
            roleService.delete(role);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }

    /**
     * 查找所有权限
     * @return
     */
    @RequestMapping("permission/tree")
    @ResponseBody
    public List<Permission> permissionTree(){
        return roleService.findAllByParentIsNull();
    }

    /**
     * 获取权限列表
     * @param id
     * @return
     */
    @RequestMapping("permission/{id}")
    @Transactional
    @ResponseBody
    public Set<Permission> permission(@PathVariable("id") Long id){
        return roleService.getPermissionByRoleId(id);
    }

    /**
     * 分配权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @RequestMapping("permission/save")
    @ResponseBody
    public JsonResult permissionSave(Long roleId, Long[] permissionId){

        roleService.savePermission(roleId, permissionId);

        return JsonResult.success("授权成功！");
    }

}
