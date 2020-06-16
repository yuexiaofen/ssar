package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.model.dao.ClassDao;
import com.sqlserver.ssar.model.dao.ClassUserDao;
import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.ClassUser;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.ClassService;
import com.sqlserver.ssar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ClassUserDao classUserDao;


    @Override
    public Class findById(Long id) {
        return classDao.findById(id).get();
    }

    @Override
    public void delete(Class cls) {
        classDao.delete(cls);
    }

    @Override
    public void save(Class cls) {
        classDao.save(cls);
    }

    @Override
    public Iterable<Class> findAll() {
        return classDao.findAll();
    }

    @Override
    public List<Class> findAllByTeacher(User teacher) {
        List<Class> classes = new ArrayList<>();
        for (ClassUser classUser : classUserDao.findAllByUser(teacher)) {
            classes.add(classUser.getClazz());
        }
        return classes;
    }
}
