package com.origin.tool.util;

import com.origin.tool.entity.MBG;
import org.junit.Test;

import static org.junit.Assert.*;

public class MBGUtilTest {

    @Test
    public void generate() throws Exception {
        MBG mbg = new MBG();
        MBG.TableConfig tableConfig = mbg.new TableConfig();
        tableConfig.setTableName("model");
        mbg.addTableName(tableConfig);
        MBGUtil.generate(mbg);
    }
}