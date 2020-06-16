package com.sqlserver.ssar.service;

import com.sqlserver.ssar.model.entity.Role;
import com.sqlserver.ssar.model.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> getUserList(int page, int rows);

    public User findById(Long id);

    public List<Role> findRolesByEnable(Boolean enable);

    public void save(User user);

    public void delete(User user);

    public int countByAccount(String account);

    public List<User> getStudentsByTeacher(User teacher);

    public List<User> getStudentsByClassId(Long classId);

    void personalSave(User user);
}
