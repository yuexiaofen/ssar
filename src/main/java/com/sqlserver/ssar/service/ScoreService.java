package com.sqlserver.ssar.service;

import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.Score;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ScoreService {
    List<Map> getScoreList(User teacher);

    List<Map> getScoreListByTeacherAndClassId(User teacher, Long id);

    List<Practice> getScoreListByStudent(User student);

    void save(Score score) throws SQLException;
}
