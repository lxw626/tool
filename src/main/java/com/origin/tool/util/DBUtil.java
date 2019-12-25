package com.origin.tool.util;

import com.origin.tool.config.GConfig;
import com.origin.tool.config.MyDataSource;
import com.origin.tool.constant.ListConstant;
import com.origin.tool.core.ColumnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);
    private static final String SQL = "SELECT * FROM ";// 数据库操作

    /**
     * 获取数据库表名
     */
    public static List<String> getTableNames(String dbName) {
        dbName = dbName.toLowerCase();
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            // 从元数据中获取到所有的表名
            if("oracle".equals(dbName)){
                // Oracle数据库的前两个参数为用户名但是必须大写！必须大写！必须大写！
                rs = db.getTables(GConfig.USERNAME.toUpperCase(), GConfig.USERNAME.toUpperCase(), null, new String[]{"TABLE"});
            }else if("mysql".equals(dbName)){
                rs = db.getTables(null, null, null, new String[]{"TABLE"});
            }else {
                Collections.emptyList();
            }
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("获取数据库中表名列表失败", e);
        }
        Collections.sort(tableNames);
        return tableNames;
    }


    /**
     * 获取给定表的字段信息
     *
     * @param tableName
     * @return
     */
    public static List<ColumnInfo> getColumnInfos(String tableName) {
        List<ColumnInfo> columnInfos = Collections.emptyList();
        if ("oracle".equals(GConfig.DBType.toLowerCase())) {
            columnInfos = getColumnInfosForOracle(tableName);
        } else if("mysql".equals(GConfig.DBType.toLowerCase())){
            columnInfos = getColumnInfosForMysql(tableName);
        }
        return columnInfos;
    }


    /**
     * 获取oracle给定表的表信息
     *
     * @param tableName
     * @return
     */
    private static List<ColumnInfo> getColumnInfosForOracle(String tableName) {
        List<ColumnInfo> columnInfos = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        Statement statement = null;
        String tableSql = SQL + tableName;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            String sql = "SELECT * FROM ( " +
                    "SELECT utc.column_name, utc.data_type, ucc.comments,utc.nullable,data_precision, data_scale " +
                    "FROM user_tab_columns utc, user_col_comments ucc " +
                    "WHERE utc.table_name = ucc.table_name AND utc.column_name = ucc.column_name AND utc.table_name =  '" + tableName + "' " +
                    "ORDER BY column_id " +
                    ") t1 LEFT JOIN (" +
                    "SELECT column_name AS pk " +
                    "FROM user_cons_columns " +
                    "WHERE constraint_name = ( " +
                    "SELECT constraint_name " +
                    "FROM user_constraints " +
                    "WHERE table_name =  '" + tableName + "' AND constraint_type = 'P' " +
                    ")" +
                    ") t2 ON t1.column_name = t2.pk ";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColumnName(rs.getString("COLUMN_NAME"));
                columnInfo.setColumnType(rs.getString("DATA_TYPE"));
                columnInfo.setColumnComment(rs.getString("COMMENTS"));
                String pk = rs.getString("PK");
                if (pk != null){
                    columnInfo.setPrimaryKey(true);
                }else {
                    columnInfo.setPrimaryKey(false);
                }
                String nullable = rs.getString("NULLABLE");
                if("Y".equals(nullable)){
                    columnInfo.setAllowNull(true);
                }else{
                    columnInfo.setAllowNull(false);
                }
                String scale = rs.getString("DATA_SCALE");
                String precision = rs.getString("DATA_PRECISION");
                if(precision!=null){
                    columnInfo.setPrecision(Integer.valueOf(precision));
                }
                if(scale!=null){
                    columnInfo.setScale(Integer.valueOf(scale));
                }
                columnInfos.add(columnInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnInfos;
    }

    /**
     * 获取mysql给定表的表信息
     * @param tableName
     * @return
     */
    private static List<ColumnInfo> getColumnInfosForMysql(String tableName) {
        List<ColumnInfo> columnInfos = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        Statement  statement = null;
        ResultSet rs = null;
        try {
            String tableSql = SQL + tableName;
            statement = conn.createStatement();
            rs = statement.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                ColumnInfo columnInfo = new ColumnInfo();
                // 设置列的名字
                columnInfo.setColumnName(rs.getString("Field"));
                String typeAndLength = rs.getString("Type");
                String type = typeAndLength;
                if(type.contains("(")){
                    type = type.substring(0,type.indexOf("("));
                }
                // 设置列的类型
                columnInfo.setColumnType(type);
                // 如果是数字列设置总位数和小数位数
                if(isMysqlNumber(type)){
                    if(typeAndLength.contains("int")){
                        columnInfo.setPrecision(Integer.parseInt(typeAndLength.substring(typeAndLength.indexOf("(")+1, typeAndLength.length() - 2)));
                        columnInfo.setScale(0);
                    }else{
                        String[] split = typeAndLength.substring(typeAndLength.indexOf("(")+1, typeAndLength.length() - 1).split(",");
                        columnInfo.setPrecision(Integer.parseInt(split[0]));
                        columnInfo.setScale(Integer.parseInt(split[1]));
                    }
                }
                // 设置列注释
                columnInfo.setColumnComment(rs.getString("Comment"));
                // 判断是否为主键
                if("PRI".equals(rs.getString("Key"))){
                    columnInfo.setPrimaryKey(true);
                }else {
                    columnInfo.setPrimaryKey(false);
                }
                // 判断是否为null
                if("YES".equals(rs.getString("Null"))){
                    columnInfo.setAllowNull(true);
                }else{
                    columnInfo.setAllowNull(false);
                }
                columnInfos.add(columnInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnInfos;
    }

    /**
     * 判断Mysql数据库中列的类型是否为数值类型
     * @param type
     * @return
     */
    private static boolean isMysqlNumber(String type){
        List<String> list = ListConstant.NUMBER_IN_MYSQL;
        for (String s : list) {
            if (s.equals(type)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = MyDataSource.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("获取数据库连接失败",e);
        }
        return connection;
    }




}
