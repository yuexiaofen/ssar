package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryDao extends PagingAndSortingRepository<Dictionary,Long> {


}
