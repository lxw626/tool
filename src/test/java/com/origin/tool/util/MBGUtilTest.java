package com.origin.tool.util;

import com.origin.tool.core.MBG;
import org.junit.Test;

import static org.junit.Assert.*;

public class MBGUtilTest {

    @Test
    public void generate() throws Exception {
        MBG mbg = new MBG();
        //mbg.addTableConfig(mbg.new TableConfig("emp")).addTableConfig(mbg.new TableConfig("dept"));
        mbg.addTableConfig("emp","Emp").addTableConfig("dept","Dept");
        MBGUtil.generate(mbg);
    }
}