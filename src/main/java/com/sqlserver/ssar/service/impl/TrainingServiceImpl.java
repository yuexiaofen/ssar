package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.common.JsonResult;
import com.sqlserver.ssar.model.dao.DictionaryDao;
import com.sqlserver.ssar.model.dao.PracticeDao;
import com.sqlserver.ssar.model.dao.TrainingLibraryDao;
import com.sqlserver.ssar.model.dao.TrainingLibraryPracticeDao;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.TrainingLibrary;
import com.sqlserver.ssar.model.entity.TrainingLibraryPractice;
import com.sqlserver.ssar.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private TrainingLibraryDao trainingLibraryDao;

    @Autowired
    private PracticeDao practiceDao;

    @Autowired
    private TrainingLibraryPracticeDao trainingLibraryPracticeDao;

    @Override
    @Transactional(readOnly = false)
    public void save(TrainingLibrary _training, Dictionary _dictionary)  {
        TrainingLibrary training = trainingLibraryDao.save(_training);
        if (training != null) {
            _dictionary.setTrainingLibrary(training);
            Dictionary dictionary = dictionaryDao.save(_dictionary);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public TrainingLibrary findById(Long id) {
        return trainingLibraryDao.findById(id).get();
    }

    @Override
    @Transactional(readOnly = false)
    public JsonResult delete(Long id) {
        TrainingLibrary training = trainingLibraryDao.findById(id).get();
        if (training != null){
            trainingLibraryDao.deleteById(id);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }

    @Override
    public List<TrainingLibrary> findAllByEnable(boolean b) {
        return trainingLibraryDao.findAllByEnable(b);
    }

    @Override
    public TrainingLibrary getTrainingLibraryById(Long id) {
        return trainingLibraryDao.findById(id).get();
    }

    @Override
    public Set<TrainingLibrary> getTrainingLibraryByPracticeId(Long id) {
        Practice practice = practiceDao.findById(id).get();
        Set<TrainingLibrary> trainingLibraryList = new HashSet<>();
        for (TrainingLibraryPractice trainingLibraryPractice : trainingLibraryPracticeDao.findAllByPractice(practice)) {
            trainingLibraryList.add(trainingLibraryPractice.getTrainingLibrary());
        }
        return trainingLibraryList;
    }


}
