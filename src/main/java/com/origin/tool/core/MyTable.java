package com.origin.tool.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MyTable {
    private String tableName;
    private String tableComment;
    private ColumnInfo columnInfo;
}
