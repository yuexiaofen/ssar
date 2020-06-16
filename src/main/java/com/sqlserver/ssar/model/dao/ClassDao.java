package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ClassDao extends CrudRepository<Class,Long> {

    Class findByIdAndEnable(Long id, boolean b);
}
