package com.sqlserver.ssar.model.dao;

import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends CrudRepository<Role,Long> {

    List<Role> findAllByEnable(boolean b);
}
