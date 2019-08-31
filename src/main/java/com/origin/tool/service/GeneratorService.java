package com.origin.tool.service;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorService {
    public void genertedByMyBatis() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        // 创建SAXReader对象
        SAXReader sr = new SAXReader(); // 需要导入jar包:dom4j
        // 关联xml
        Document document = sr.read(".\\src\\main\\resources\\mbg.xml");
        String treeUrl = "//commentGenerator/property";
        Element element = (Element)document.selectSingleNode(treeUrl);
        element.addAttribute("value","false");


        File configFile = new File(".\\src\\main\\resources\\mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
