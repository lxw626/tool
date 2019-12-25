package com.origin.tool.service;

import com.origin.tool.core.MBG;
import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class GeneratorServiceTest {

    /*@Autowired
    GeneratorService generatorService;*/
    GeneratorService generatorService = new GeneratorService();


    @Test
    public void genertedByMyBatis() throws Exception {
        MBG mbg = new MBG();
        mbg.addTableConfig("emp","Emp").addTableConfig("dept","Dept");
        generatorService.genertedByMyBatis(mbg);
    }
    @Test
    public void genertedByMyBatisWithXml() throws Exception {
        generatorService.genertedByMyBatisWithXml();
    }
}