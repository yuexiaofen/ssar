package com.sqlserver.ssar;

import com.sqlserver.ssar.common.DBManager;
import com.sqlserver.ssar.common.GetDictionary;
import com.sqlserver.ssar.common.ListEqual;
import com.sqlserver.ssar.model.dao.PracticeDao;
import com.sqlserver.ssar.model.dao.UserDao;
import com.sqlserver.ssar.model.entity.Dictionary;
import com.sqlserver.ssar.model.entity.Practice;
import com.sqlserver.ssar.model.entity.User;
import com.sqlserver.ssar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class SsarApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PracticeDao practiceDao;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() throws SQLException {
        List<Dictionary> dictionaries = GetDictionary.getDictionary();
        System.out.println(dictionaries);
//        Long id = (long)10004;
//        User teacher = userDao.findById(id).get();
////        System.out.println(teacher);
//        List<Practice> practices = practiceDao.findAllByEnableAndTeacher(true, teacher);
//        System.out.println(practices);

    }
}
