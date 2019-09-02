package com.origin.tool.service;

import com.origin.tool.entity.MBG;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class GeneratorServiceTest {

    /*@Autowired
    GeneratorService generatorService;*/
    GeneratorService generatorService = new GeneratorService();


    @Test
    public void genertedByMyBatis() throws Exception {
        MBG mbg = new MBG();
        mbg.init("D:\\test");
        System.out.println(mbg.getEntityPath());
        System.out.println(mbg.getMapperPath());
        System.out.println(mbg.getXmlPath());
        mbg.setTargetRuntime("MyBatis3");
        generatorService.genertedByMyBatis(mbg);
    }
}