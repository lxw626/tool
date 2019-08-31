package com.origin.tool.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import static org.junit.Assert.*;

public class XmlUtilTest {

    @Test
    public void createXML() throws Exception {
        String path = "./xmltest/test.xml";
        XmlUtil.createXML(path);
        XmlUtil.parsingXML(path);
    }
    @Test
    public void parsingXML() throws Exception {
        String path = "./src/main/resources/mbg.xml";
        XmlUtil.parsingXML(path);
    }

    @Test
    public void xmlElementToHashmap_url() throws Exception {
        String path = "./src/main/resources/mbg.xml";
        //创建解析器
        SAXReader reader = new SAXReader();//创建读取文件内容对象
        Document document = reader.read(path);//指定文件并读取
        String treeUrl = "//commentGenerator/property";
        XmlUtil.xmlElementToHashmap_url(document,treeUrl);

    }
    @Test
    public void updateAttribute() throws Exception {
        String path = "./src/main/resources/mbg.xml";
        //创建解析器
        SAXReader reader = new SAXReader();//创建读取文件内容对象
        Document document = reader.read(path);//指定文件并读取
        String treeUrl = "//commentGenerator/property";
        XmlUtil.updateAttribute(document,treeUrl);

    }


}