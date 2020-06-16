package com.sqlserver.ssar.service;

import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public Iterable findAll();

    public Role findById(Long id);

    public void save(Role role);

    public void delete(Role role);

    public List<Permission> findAllByParentIsNull();

    public Set<Permission> getPermissionByRoleId(Long id);

    public void savePermission(Long roleId, Long[] permissionId);
}
