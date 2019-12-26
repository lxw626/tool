package com.origin.tool.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Dept extends BasicEntity{
    private Long deptno;

    private String dname;

    private String loc;

}