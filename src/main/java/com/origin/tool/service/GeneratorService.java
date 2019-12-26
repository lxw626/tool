package com.origin.tool.service;

import org.springframework.stereotype.Service;
import util.mbg.MBG;
import util.mbg.MBGUtil;

@Service
public class GeneratorService {

    public void genertedByMyBatis(MBG mbg) throws Exception {
        MBGUtil.generate(mbg);
    }
    public void genertedByMyBatisWithXml() throws Exception {
        MBGUtil.generateWithXml();
    }


}
