package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDao extends CrudRepository<Grade,Long> {


}
