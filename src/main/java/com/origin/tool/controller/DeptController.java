package com.origin.tool.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.tool.entity.Dept;
import com.origin.tool.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lixiewen
 * @create 2019-11-27 15:41
 */
@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @PostMapping("/getDeptsBypage")
    public PageInfo getDeptsBypage(@RequestBody Dept params){
        Integer currentPage = params.getCurrentPage();
        Integer pageSize = params.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        List<Dept> depts = deptService.getDepts(params);
        return new PageInfo(depts);
    }


}
