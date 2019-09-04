package com.origin.tool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class MBG {
    private String driverClass = "com.mysql.jdbc.Driver";
    private String connectionURL = "jdbc:mysql://localhost:3306/tool";
    private String username = "root";
    private String password = "root";
    private String targetRuntime = "MyBatis3Simple";
    private String entityPackage = "com.origin.tool.entity";
    private String entityPath = "";
    private String rootClass = "com.origin.tool.entity.BasicEntity";
    private String mapperPackage = "com.origin.tool.mapper";
    private String rootInterface = "com.origin.tool.mapper.BasicMapper";
    private String mapperPath = "";
    private String mapperType = "";
    private String xmlPackage = "com.origin.tool.mapper";
    private String xmlPath = "";
    private Boolean serializableSwitch = true;
    private Boolean toStringSwitch = true;
    private Boolean commentSwitch = true;
    private Boolean bigDecimalSwitch = true;
    private Boolean trimStringSwitch = true;
    private List<TableConfig> tableNames = new ArrayList<>();

    public List<TableConfig> addTableName(TableConfig tableConfig) {
        tableNames.add(tableConfig);
        return tableNames;
    }

    @Setter
    @Getter
    @ToString
    public class TableConfig {
        private String tableName;
        private String entityName;

    }
}

