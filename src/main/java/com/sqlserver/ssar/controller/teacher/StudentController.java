package com.sqlserver.ssar.controller.teacher;

import com.sqlserver.ssar.model.dao.ClassUserDao;
import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.ClassUser;
import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.ClassService;
import com.sqlserver.ssar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/teacher/student")
@Slf4j
public class StudentController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClassUserDao classUserDao;

    @RequestMapping
    public void index(){}

    @RequestMapping("/stulist")
    public List<User> list(HttpSession session){
        User teacher = (User) session.getAttribute("user");
        List<User> students = userService.getStudentsByTeacher(teacher);
        return students;
    }

    @RequestMapping("/class")
    public List<Class> calsslist(HttpSession session){
        User teacher = (User) session.getAttribute("user");
        List<Class> teacherClasses = new ArrayList<>();
        for (ClassUser classUser : classUserDao.findAllByUser(teacher)) {
            teacherClasses.add(classUser.getClazz());
        }
        return teacherClasses;
    }

    @RequestMapping("/class/student")
    public List<User> studentList(@PathVariable("classId") Long classId){

        return userService.getStudentsByClassId(classId);
    }
}
