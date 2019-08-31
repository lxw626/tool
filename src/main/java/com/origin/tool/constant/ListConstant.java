package com.origin.tool.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ListConstant {
    List<String> NUMBER_IN_MYSQL = new ArrayList<>(Arrays.asList(
           "tinyint","smallint","mediumint","int","integer",
            "bigint","float","double","decimal"
    ));
}
