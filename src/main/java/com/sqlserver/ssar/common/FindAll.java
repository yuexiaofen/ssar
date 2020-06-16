package com.sqlserver.ssar.common;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FindAll {
    public static Map<String, Object> findAllData(){
        Map<String, Object> map = new HashMap<String, Object>();
        String sql1 = "select * from score";
        String sql2 = "select * from subject";
        String sql3 = "select * from student";
        String sql4 = "select * from class";

        DBManager dbManager = new DBManager();
        ResultSet query1 = dbManager.query(sql1);
        ResultSet query2 = dbManager.query(sql2);
        ResultSet query3 = dbManager.query(sql3);
        ResultSet query4 = dbManager.query(sql4);

        map.put("score", query1);
        map.put("subject", query2);
        map.put("student", query3);
        map.put("class", query4);

        return map;
    }
}
