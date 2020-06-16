package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingLibraryDao extends CrudRepository<TrainingLibrary,Long> {

    List<TrainingLibrary> findAllByEnable(boolean b);

    List<TrainingLibrary> findAllByEnableAndId(boolean b, Long id);
}

