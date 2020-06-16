package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.model.dao.*;
import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.ClassUser;
import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ClassUserDao classUserDao;

    @Autowired
    private PracticeClassDao practiceClassDao;

    @Override
    public Map<String, Object> getUserList(int page, int rows) {
        PageRequest pr = PageRequest.of(page -1,rows, Sort.Direction.DESC,"id");
        Page<User> usersByPage = userDao.findAll(pr);
        Map<String, Object> data = new HashMap<>();
        data.put("total",usersByPage.getTotalElements());
        data.put("rows",usersByPage.getContent());
        return data;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<Role> findRolesByEnable(Boolean enable) {
        return roleDao.findAllByEnable(true);
    }

    @Override
    public void save(User user) {
        if(user.getId() == null){
            // md5 加密密码
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        } else {
            User org = userDao.findById(user.getId()).get();
            if(user.getPassword() != null && !user.getPassword().isEmpty()){
                user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            } else {
                // 密码为空，不修改密码
                user.setPassword(org.getPassword());
            }
        }
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public int countByAccount(String account) {
        return userDao.countByAccount(account);
    }

    @Override
    public List<User> getStudentsByTeacher(User teacher) {
        List<User> students = new ArrayList<>();
        for (ClassUser classUser : classUserDao.findAllByUser(teacher)) {
            for (Role role : classUser.getUser().getRoles()) {
                if (role.getRoleKey().equals("student")){
                    students.add(classUser.getUser());
                }
            }
        }
        return students;
    }

    @Override
    public List<User> getStudentsByClassId(Long classId) {
        List<User> students = new ArrayList<>();
        Class aClass = classDao.findById(classId).get();
        for (ClassUser classUser : classUserDao.findAllByClazz(aClass)) {
            for (Role role : classUser.getUser().getRoles()) {
                if ("student".equals(role.getRoleKey())) {
                    students.add(classUser.getUser());
                    break;
                }
            }
        }
        return students;
    }

    @Override
    public void personalSave(User user) {
        if(user.getId() == null){
            // md5 加密密码
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        } else {
            User org = userDao.findById(user.getId()).get();
            if(user.getPassword() != null && !user.getPassword().isEmpty()){
                user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            } else {
                // 密码为空，不修改密码
                user.setPassword(org.getPassword());
            }
        }
        User user1 = userDao.findById(user.getId()).get();
        user1.setPassword(user.getPassword());
        user1.setTel(user.getTel());
        user1.setEmail(user.getEmail());
        userDao.save(user1);
    }
}
