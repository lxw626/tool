package com.origin.tool.config;


import com.alibaba.druid.pool.DruidDataSource;

/**
 * @autor lixiewen
 * @date 2019/4/18-15:08
 */
public class MyDataSource {
    static DruidDataSource dataSource;
    static{
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName(GConfig.DRIVER);
        dataSource.setUrl(GConfig.URL);
        dataSource.setUsername(GConfig.USERNAME);
        dataSource.setPassword(GConfig.PASSWORD);
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
