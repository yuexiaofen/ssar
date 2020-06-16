package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.model.dao.PermissionDao;
import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 通过id查找权限
     * @param id
     * @return
     */
    @Override
    public Permission findById(Long id) {
        return permissionDao.findById(id).get();
    }

    /**
     * 查找所有父目录的权限
     * @return
     */
    @Override
    public List<Permission> findAllByParentIsNull() {
        List<Permission> roots = permissionDao.findAllByParentIsNull();
        this.revers(roots);
        return roots;
    }

    /**
     * 保存权限
     * @param permission
     */
    @Override
    @Transactional(readOnly = false)
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    /**
     * 删除权限
     * @param permission
     */
    @Override
    public void delete(Permission permission) {
        permissionDao.delete(permission);
    }


    /**
     * 递归子节点
     * @param nodes
     */
    private void revers(List<Permission> nodes){
        for (Permission root : nodes) {
            List<Permission> children = permissionDao.findAllByParent(root);
            this.revers(children);
            root.setChildren(children);
        }
    }
}
