package com.origin.tool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class MBG {
    private String projectPath = ".";
    private String targetRuntime;
    private String driverClass;
    private String connectionURL;
    private String userId;
    private String password;
    private String entityPackage;
    private String entityPath = projectPath+"\\src\\main\\java";
    private String mapperPackage;
    private String mapperPath = projectPath+"\\src\\main\\java";
    private String xmlPackage;
    private String xmlPath = projectPath+"\\src\\main\\resources";
    private List<Map<String,String>> tableAndEntitys = Collections.emptyList();
    public void init(String projectPath){
        this.setProjectPath(projectPath);
        entityPath = projectPath+"\\src\\main\\java";
        mapperPath = projectPath+"\\src\\main\\java";
        xmlPath = projectPath+"\\src\\main\\resources";
    }
}
