package com.origin.myScript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.vue.MVueUtil;

import java.io.IOException;

/**
 * @author lixiewen
 * @create 2019-12-26 16:12
 */
public class IndexHtmlGenerator {
    private static Logger logger = LoggerFactory.getLogger(IndexHtmlGenerator.class);
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            MVueUtil.indexGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("index.html重新生成完毕,编译后即可生效,耗时:"+(end-start)+"ms");
    }
}
