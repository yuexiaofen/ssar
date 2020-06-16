package com.sqlserver.ssar.controller;

import com.sqlserver.ssar.common.Menu;
import com.sqlserver.ssar.model.dao.PermissionDao;
import com.sqlserver.ssar.model.dao.UserDao;
import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.model.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Value("${system.super.user.id}")
    private Long superId;

    @RequestMapping
    public String index(@SessionAttribute(value = "user", required = false) User user){

        if (user == null){
            // 未登录
            return "login";
        }
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String account, @RequestParam String password, RedirectAttributes rda){
        User user = userDao.findFirstByAccount(account);
        // 判断账号是否可用
        if(user != null && user.getEnable()){
            // 判断密码是否匹配
            if (user.getPassword().equals(DigestUtils.md5Hex(password))){

                Set<Permission> permissions ;
                // 判断是否是超级管理员
                if (Objects.equals(superId, user.getId())){
                    permissions = permissionDao.findAllByEnableOrderByWeightDesc(true);
                } else {
                    // 获取用户菜单
                    Set<Role> roles = user.getRoles();
                    permissions = new HashSet<>();
                    roles.forEach(role -> permissions.addAll(role.getPermissions()));
                }

                // 存储菜单
                TreeSet<Permission> menus = new TreeSet<>((o1,o2)-> {
                    if(Objects.equals(o2.getWeight(), o1.getWeight())){
                        return  -1;
                    }
                    return o1.getWeight() - o2.getWeight();
                });

                // 存储权限Key
                Set<String> keys = new HashSet<>();
                // 所有有权限访问的请求
                Set<String> urls = new HashSet<>();

                permissions.forEach(permission -> {
                    if(permission.getEnable()){
                        if(permission.getType().equals(Permission.Type.MENU)){
                            // 菜单
                            menus.add(permission);
                            urls.add(permission.getPath());
                        }
                        // 拥有权限
                        keys.add(permission.getPermissionKey());

                        urls.addAll(Arrays.asList(permission.getResource().split(",")));
                    }
                });
                // 树形数据转换
                List<Menu> menuList = new ArrayList<>();
                menus.forEach(permission -> {
                    Menu m = new Menu();
                    m.setId(permission.getId());
                    m.setText(permission.getName());
                    m.setParentId(permission.getParent() == null?null:permission.getParent().getId());
                    m.setHref(permission.getPath());
                    menuList.add(m);
                });
                // 获取用户拥有的权限
                session.setAttribute("user", user);
                session.setAttribute("menus", menuList);
                session.setAttribute("keys", keys);
                session.setAttribute("urls", urls);
            } else {
                rda.addFlashAttribute("error","账号或密码错误！");
            }
        } else {
            rda.addFlashAttribute("error","账号不可用！");
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/menus")
    @ResponseBody
    public List<Menu> menus(@SessionAttribute("menus") List<Menu> menuList){
        return menuList;
    }

    @RequestMapping("/reject")
    public String reject(){
        return "reject";
    }
}

