package com.sqlserver.ssar.service;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface TrainingService {
    void save(TrainingLibrary training, Dictionary dictionary);

    TrainingLibrary findById(Long id);

    JsonResult delete(Long id);

    List<TrainingLibrary> findAllByEnable(boolean b);

    TrainingLibrary getTrainingLibraryById(Long id);

    Set<TrainingLibrary> getTrainingLibraryByPracticeId(Long id);
}
