package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.model.dao.GradeDao;
import com.sqlserver.ssar.model.entity.Grade;
import com.sqlserver.ssar.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;


    @Override
    public Grade findById(Long id) {
        return gradeDao.findById(id).get();
    }

    @Override
    public Iterable<Grade> findAll() {
        return gradeDao.findAll();
    }

    @Override
    public void save(Grade grade) {
        gradeDao.save(grade);
    }

    @Override
    public void delete(Grade grade) {
        gradeDao.delete(grade);
    }
}
