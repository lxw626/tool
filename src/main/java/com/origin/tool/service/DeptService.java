package com.origin.tool.service;

import com.origin.tool.entity.Dept;
import com.origin.tool.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lixiewen
 * @create 2019-11-27 15:43
 */
@Service
public class DeptService {
    @Autowired
    DeptMapper deptMapper;

    public List<Dept> getDepts(Dept record){
        return deptMapper.select(record);
    }



}
