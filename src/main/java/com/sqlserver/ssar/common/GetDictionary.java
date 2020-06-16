package com.sqlserver.ssar.common;

import com.sqlserver.ssar.model.entity.Dictionary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDictionary {


    private static final String  sql = "SELECT   TOP (100) PERCENT CASE WHEN col.colorder = 1 THEN obj.name ELSE obj.name END AS table_name, col.colorder AS serial_number, \n" +
            "\n" +
            "                col.name AS column_name, ISNULL(ep.value, N'') AS column_description, t.name AS data_type, col.length AS length, \n" +
            "\n" +
            "                ISNULL(COLUMNPROPERTY(col.id, col.name, 'Scale'), 0) AS decimal_places, CASE WHEN COLUMNPROPERTY(col.id, \n" +
            "\n" +
            "                col.name, 'IsIdentity') = 1 THEN '√' ELSE '' END AS logo, CASE WHEN EXISTS\n" +
            "\n" +
            "                    (SELECT   1\n" +
            "\n" +
            "                     FROM      dbo.sysindexes si INNER JOIN\n" +
            "\n" +
            "                                     dbo.sysindexkeys sik ON si.id = sik.id AND si.indid = sik.indid INNER JOIN\n" +
            "\n" +
            "                                     dbo.syscolumns sc ON sc.id = sik.id AND sc.colid = sik.colid INNER JOIN\n" +
            "\n" +
            "                                     dbo.sysobjects so ON so.name = si.name AND so.xtype = 'PK'\n" +
            "\n" +
            "                     WHERE   sc.id = col.id AND sc.colid = col.colid) THEN '√' ELSE '' END AS primary_key, \n" +
            "\n" +
            "                CASE WHEN col.isnullable = 1 THEN '√' ELSE '' END AS allowEmpty, ISNULL(comm.text, N'') AS defaults\n" +
            "\n" +
            "FROM      dbo.syscolumns AS col LEFT OUTER JOIN\n" +
            "\n" +
            "                dbo.systypes AS t ON col.xtype = t.xusertype INNER JOIN\n" +
            "\n" +
            "                dbo.sysobjects AS obj ON col.id = obj.id AND obj.xtype = 'U' AND obj.status >= 0 LEFT OUTER JOIN\n" +
            "\n" +
            "                dbo.syscomments AS comm ON col.cdefault = comm.id LEFT OUTER JOIN\n" +
            "\n" +
            "                sys.extended_properties AS ep ON col.id = ep.major_id AND col.colid = ep.minor_id AND \n" +
            "\n" +
            "                ep.name = 'MS_Description' LEFT OUTER JOIN\n" +
            "\n" +
            "                sys.extended_properties AS epTwo ON obj.id = epTwo.major_id AND epTwo.minor_id = 0 AND \n" +
            "\n" +
            "                epTwo.name = 'MS_Description'\n" +
            "\n" +
            "ORDER BY obj.name, serial_number";
    public String getSql() {
        return sql;
    }

    public static List<Dictionary> getDictionary() {
        DBManager dbManager = new DBManager();

        List<Dictionary> dictionaries = new ArrayList<>();
        try {
            ResultSet resultSet = dbManager.query(sql);

            while (resultSet.next()) {
                Dictionary dictionary = new Dictionary();
                dictionary.setTableName(resultSet.getString("table_name"));
                dictionary.setSerialNumber(resultSet.getString("serial_number"));

                    dictionary.setColumnName(resultSet.getString("column_name"));

                dictionary.setColumnDescription(resultSet.getString("column_description"));
                dictionary.setDataType(resultSet.getString("data_type"));
                dictionary.setLength(resultSet.getString("length"));
                dictionary.setDecimalPlaces(resultSet.getString("decimal_places"));
                dictionary.setLogo(resultSet.getString("logo"));
                dictionary.setPrimaryKey(resultSet.getString("primary_key"));
                dictionary.setDefaults(resultSet.getString("defaults"));
                dictionaries.add(dictionary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dictionaries;
    }
}
