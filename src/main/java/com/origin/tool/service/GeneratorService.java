package com.origin.tool.service;

import com.origin.tool.core.MBG;
import com.origin.tool.util.MBGUtil;
import org.springframework.stereotype.Service;

@Service
public class GeneratorService {

    public void genertedByMyBatis(MBG mbg) throws Exception {
        MBGUtil.generate(mbg);
    }
    public void genertedByMyBatisWithXml() throws Exception {
        MBGUtil.generateWithXml();
    }


}
