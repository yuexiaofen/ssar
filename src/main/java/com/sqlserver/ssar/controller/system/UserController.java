package com.sqlserver.ssar.controller.system;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.model.entity.User;
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
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/system/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public void index(){}

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int rows){
        Map<String, Object> data = userService.getUserList(page, rows);
        return data;
    }

    @RequestMapping({"/form","/load"})
    public String form(Long id, Model model){
        if(id != null){
            // 编辑
            User user = userService.findById(id);
            model.addAttribute("user",user);
        }
        // 查询角色
        model.addAttribute("roles",userService.findRolesByEnable(true));
        return "system/user/form";
    }

    @PostMapping({"/save","/update"})
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult form(@Valid User user, BindingResult br){
        if(!br.hasErrors()){
            userService.save(user);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id){
        User user = userService.findById(id);
        if (user != null){
            userService.delete(user);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }


    @RequestMapping("/check")
    @ResponseBody
    public String check(String account){
        if(userService.countByAccount(account) == 0){
            return "true";
        }
        return "false";
    }

    /**
     * 个人中心展示
     * @return
     */
    @RequestMapping("/personal")
    @ResponseBody
    public Model personal(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("user",user);
        // 查询角色
        String role = "";
        int i = 0;
        Set<Role> roles = user.getRoles();
        for (Role role1 : roles) {
            if (i < roles.size()-1)
                role += role1.getRoleName()+ ",";
            else
                role += role1.getRoleName();
        }
        model.addAttribute("roles", role);
        return model;

    }

    /**
     * 个人中心信息修改
     * @param user
     * @param br
     * @return
     */
    @PostMapping("/personal/update")
    @ResponseBody
    @Transactional(readOnly = false)// 默认为false，可以不加
    public JsonResult updatePersonal(@Valid User user, BindingResult br){
        if(!br.hasErrors()){
            userService.personalSave(user);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过");
        }
    }
}
