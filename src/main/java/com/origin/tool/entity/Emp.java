package com.origin.tool.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Emp extends BasicEntity{
    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private BigDecimal sal;

    private BigDecimal comm;

    private Integer deptno;

}