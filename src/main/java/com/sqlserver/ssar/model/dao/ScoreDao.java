package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.Score;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScoreDao extends PagingAndSortingRepository<Score,Long> {
    Set<Score> findALlByPracticeAndStudent(Practice practice, User student);
}
