package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.ClassUser;
import com.sqlserver.ssar.model.entity.PracticeClass;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeClassDao extends CrudRepository<PracticeClass,Long>  {
    List<PracticeClass> findAllByClazz(Class clazz);
}
