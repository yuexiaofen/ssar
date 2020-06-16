package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.model.entity.TrainingLibraryPractice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingLibraryPracticeDao extends CrudRepository<TrainingLibraryPractice,Long>  {
    List<TrainingLibraryPractice> findAllByPractice(Practice practice);
}
