package com.origin.tool.util;

import com.origin.tool.config.GConfig;
import com.origin.tool.entity.ColumnInfo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DBUtilTest {

    @Test
    public void getTableNamesForMysql() {
       dbConfig("mysql");
        List<String> tableNames = DBUtil.getTableNames("mysql");
        tableNames.forEach((tableName)->System.out.println(tableName));
    }
    @Test
    public void getTableNamesForOracle() {
        dbConfig("oracle");
        List<String> tableNames = DBUtil.getTableNames("oracle");
        tableNames.forEach((tableName)->System.out.println(tableName));
    }
    @Test
    public void getColumnInfosForMysql() {
        dbConfig("mysql");
        List<ColumnInfo> menus = DBUtil.getColumnInfos("menu");
        menus.forEach((menu)->System.out.println(menu));

    }
    @Test
    public void getColumnInfosForOracle() {
        dbConfig("oracle");
        List<ColumnInfo> menus = DBUtil.getColumnInfos("MES_MD_MAT");
        menus.forEach((menu)->System.out.println(menu));
    }




    private static void dbConfig(String dbType){
        dbType = dbType.toLowerCase();
        if("oracle".equals(dbType)){
            GConfig.DBType = "oracle";
            GConfig.DRIVER = "oracle.jdbc.driver.OracleDriver";
            GConfig.URL = "jdbc:oracle:thin:@//localhost:1521/ORCL";
            GConfig.USERNAME = "scott";
            GConfig.PASSWORD = "tiger";
        }else if("mysql".equals(dbType)){
            GConfig.DBType = "mysql";
            GConfig.DRIVER = "com.mysql.jdbc.Driver";
            GConfig.URL = "jdbc:mysql://localhost:3306/mytool?useUnicode=true&characterEncoding=utf8&useSSL=true";
            GConfig.USERNAME = "root";
            GConfig.PASSWORD = "root";
        }

    }

}