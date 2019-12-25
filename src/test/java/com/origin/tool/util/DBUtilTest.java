package com.origin.tool.util;

import com.origin.tool.config.GConfig;
import com.origin.tool.core.ColumnInfo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DBUtilTest {

    @Test
    public void getTableNamesForMysql() {
       dbConfig("mysql");
        List<String> tableNames = DBUtil.getTableNames("mysql");
//        tableNames.forEach((tableName)->System.out.println(tableName));
        for (String tableName : tableNames) {
            if(tableName.contains("instance")){
                System.out.println(tableName);
            }
        }
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
        List<ColumnInfo> emps = DBUtil.getColumnInfos("emp");
        emps.forEach((emp)->System.out.println(emp));

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
            GConfig.URL = "jdbc:mysql://172.17.163.142:3306/riil_product?useUnicode=true&characterEncoding=utf8&useSSL=true";
            GConfig.USERNAME = "riil";
            GConfig.PASSWORD = "r4rfde32wsaq1";
        }

    }

}