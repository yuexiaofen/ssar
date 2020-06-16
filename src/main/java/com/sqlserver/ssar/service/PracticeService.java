package com.sqlserver.ssar.service;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PracticeService {

    Practice findById(Long id);

    void save(Practice practice, Long[] ids);

    JsonResult delete(Long id);

    List<Practice>  findAllByTeacher(User teacher);

    List<Practice> findAllByTeacherAndDone(User student, boolean b);
}
