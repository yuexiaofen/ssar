package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Class;
import com.sqlserver.ssar.model.entity.ClassUser;
import com.sqlserver.ssar.model.entity.TrainingLibraryPractice;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassUserDao extends CrudRepository<ClassUser,Long>  {
    List<ClassUser> findAllByUser(User teacher);

    List<ClassUser> findAllByClazz(Class aClass);
}
