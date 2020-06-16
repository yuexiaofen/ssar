package com.sqlserver.ssar.service.impl;

import com.sqlserver.ssar.model.dao.PermissionDao;
import com.sqlserver.ssar.model.dao.RoleDao;
import com.sqlserver.ssar.model.entity.Permission;
import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查找所有角色
     * @return
     */
    @Override
    public Iterable findAll() {
        return roleDao.findAll();
    }

    /**
     * 通过id查找角色
     * @param id
     * @return
     */
    @Override
    public Role findById(Long id) {
        return roleDao.findById(id).get();
    }

    /**
     * 保存一个角色
     * @param role
     */
    @Override
    @Transactional(readOnly = false)
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * 删除一个角色
     * @param role
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Role role) {
        roleDao.delete(role);
    }

    /**
     * 查找所有权限,除权限管理
     * @return
     */
    @Override
    public List<Permission> findAllByParentIsNull() {
        /*List<Permission> permissions = new ArrayList<>();
        Iterator iter = permissions.iterator();
        //通过循环迭代
        //hasNext():判断是否存在下一个元素
        while(iter.hasNext()){
            //如果存在，则调用next实现迭代
            Permission next = (Permission)iter.next();
            if(next.getPermissionKey().contains("system/permission")) {
                iter.remove();
            }
        }

        for (Permission permission : permissionDao.findAllByParentIsNull()) {
            List<Permission> children = permissionDao.findAllByParent(permission);

        }
        return permissions;*/
        return permissionDao.findAllByParentIsNull();
    }


    /**
     * 通过角色id查找权限
     * @param id
     * @return
     */
    @Override
    public Set<Permission> getPermissionByRoleId(Long id) {
        Role role = roleDao.findById(id).get();
        return role.getPermissions();
    }

    /**
     * 通过角色id和权限id给角色授权
     * @param roleId
     * @param permissionId
     */
    @Override
    public void savePermission(Long roleId, Long[] permissionId) {
        Role role = roleDao.findById(roleId).get();
        // 先清除已有角色
        role.getPermissions().clear();
        for (Long pid : permissionId) {
            role.getPermissions()
                    .add(permissionDao.findById(pid).get());
        }
        roleDao.save(role);
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
