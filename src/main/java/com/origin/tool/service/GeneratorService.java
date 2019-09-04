package com.origin.tool.service;

import com.origin.tool.entity.MBG;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorService {
    private static String defaultConfigFilePath = ".\\src\\main\\resources\\mbg.xml";
    private static String customizedConfigFilePath = ".\\src\\main\\resources\\customizedMbg.xml";

    public void genertedByMyBatis(MBG mbg) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile;
        if(mbg!=null){
            mbgconfig(mbg);
            configFile = new File(customizedConfigFilePath);
        }else{
            configFile = new File(defaultConfigFilePath);
        }
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    public static void mbgconfig(MBG mbg) throws Exception {
        // 创建SAXReader对象
        SAXReader sr = new SAXReader();
        // 关联xml读取默认配置
        Document document = sr.read(defaultConfigFilePath);
        // 配置生成简单版还是豪华版
        Element context = (Element)document.selectSingleNode("//context");
        if(mbg.getTargetRuntime()!=null){
            context.addAttribute("targetRuntime",mbg.getTargetRuntime());
        }
        // 配置要解析的表
//        List<Map<String, String>> tableAndEntitys = mbg.getTableAndEntitys();
        List<MBG.TableConfig> tableConfigs = mbg.getTableNames();
        if(tableConfigs.size()>0){
            for (MBG.TableConfig tableConfig : tableConfigs) {
                Element table = context.addElement("table");
                table.addAttribute("tableName",tableConfig.getTableName());
                table.addAttribute("domainObjectName",tableConfig.getEntityName());
            }
        }
        // 配置连接
        Element jdbcConnection = (Element)document.selectSingleNode("//jdbcConnection");
        if(mbg.getDriverClass()!=null){
            jdbcConnection.addAttribute("driverClass",mbg.getDriverClass());
        }
        if(mbg.getConnectionURL()!=null){
            jdbcConnection.addAttribute("connectionURL",mbg.getConnectionURL());
        }
        if(mbg.getUsername()!=null){
            jdbcConnection.addAttribute("userId",mbg.getUsername());
        }
        if(mbg.getPassword()!=null){
            jdbcConnection.addAttribute("password",mbg.getPassword());
        }
        // 配置实体类
        Element javaModelGenerator = (Element)document.selectSingleNode("//javaModelGenerator");
        if(mbg.getEntityPackage()!=null){
            javaModelGenerator.addAttribute("targetPackage",mbg.getEntityPackage());
        }
        if(mbg.getEntityPath()!=null){
            javaModelGenerator.addAttribute("targetProject",mbg.getEntityPath());
        }
        // 配置Mapp接口
        Element javaClientGenerator = (Element)document.selectSingleNode("//javaClientGenerator");
        if(mbg.getMapperPackage()!=null){
            javaClientGenerator.addAttribute("targetPackage",mbg.getMapperPackage());
        }
        if(mbg.getMapperPath()!=null){
            javaClientGenerator.addAttribute("targetProject",mbg.getMapperPath());
        }
        // 配置Mapp接口
        Element sqlMapGenerator = (Element)document.selectSingleNode("//sqlMapGenerator");
        if(mbg.getXmlPackage()!=null){
            sqlMapGenerator.addAttribute("targetPackage",mbg.getXmlPackage());
        }
        if(mbg.getXmlPath()!=null){
            sqlMapGenerator.addAttribute("targetProject",mbg.getXmlPath());
        }
        XMLWriter output = new XMLWriter(new FileWriter(new File(customizedConfigFilePath)));
        output.write(document);
        output.close();
    }

}
