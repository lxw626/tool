package com.origin.tool.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorServiceTest {

    @Autowired
    GeneratorService generatorService;


    @Test
    public void genertedByMyBatis() throws Exception {
        generatorService.genertedByMyBatis();
    }
}