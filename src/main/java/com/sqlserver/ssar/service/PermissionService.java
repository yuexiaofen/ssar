package com.sqlserver.ssar.service;

import com.sqlserver.ssar.model.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionService {

    public Permission findById(Long id);

    public List<Permission> findAllByParentIsNull();

    public void save(Permission permission);

    public void delete(Permission permission);


}
