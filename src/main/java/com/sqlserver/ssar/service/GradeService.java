package com.sqlserver.ssar.service;

import com.sqlserver.ssar.model.entity.Grade;
import com.sqlserver.ssar.model.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GradeService {


    Grade findById(Long id);

    Iterable<Grade> findAll();

    void save(Grade grade);

    void delete(Grade grade);
}
