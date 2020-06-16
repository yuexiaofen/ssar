package com.sqlserver.ssar.service;

import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.User;

import java.util.List;

public interface ClassService {


    Class findById(Long id);

    void delete(Class cls);

    void save(Class cls);

    Iterable<Class> findAll();

    List<Class> findAllByTeacher(User teacher);
}
