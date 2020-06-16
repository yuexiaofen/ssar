package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PracticeDao extends CrudRepository<Practice,Long> {

    List<Practice> findAllByEnableAndTeacher(boolean b, User teacher);

    List<Practice> findAllByEnableAndTeacherAndDone(boolean b, User student, boolean b1);
}
