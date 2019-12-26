package com.origin.tool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import util.mbg.MBG;
import util.mbg.MBGUtil;

/**
 * @author lixiewen
 * @create 2019-11-27 15:45
 */
@RestController
public class MBGController {
    @GetMapping("/genertedByMyBatis")
    public void genertedByMyBatis(MBG mbg) throws Exception {
        MBGUtil.generate(mbg);
    }
}
