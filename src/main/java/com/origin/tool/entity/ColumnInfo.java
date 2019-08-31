package com.origin.tool.entity;


import com.origin.tool.config.GautoConfig;

public class ColumnInfo {
    private String columnName;
    private String columnType;
    private String fieldName;
    private String fieldType;
    private String jdbcType;
    private String columnComment;
    // 是否是主键
    private Boolean primaryKey;
    // 是否允许null
    private Boolean allowNull;
    // 总位数
    private Integer precision;
    // 小数位数
    private Integer scale;

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Boolean getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(Boolean allowNull) {
        this.allowNull = allowNull;
    }

    public ColumnInfo() {
    }

    public ColumnInfo(String columnName, String fieldName, String fieldType, String jdbcType) {
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.jdbcType = jdbcType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        this.fieldName = GautoConfig.getFieldName(columnName);
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
        this.fieldType = GautoConfig.getFieldType(columnType,precision,scale);
        this.jdbcType = GautoConfig.getJdbcType(columnType);

    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnName='" + columnName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", precision=" + precision +
                ", scale=" + scale +
                '}';
    }
}
