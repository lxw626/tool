package com.origin.tool.service;

import com.origin.tool.entity.Emp;
import com.origin.tool.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lixiewen
 * @create 2019-11-27 15:42
 */
@Service
public class EmpService {
    @Autowired
    EmpMapper empMapper;

    public List<Emp> getEmps(Emp record){
        return empMapper.select(record);
    }
    public List<String> jobs(){
        return empMapper.jobs();
    }
    public List<String> deptnos(){
        return empMapper.deptnos();
    }
    public int insert(Emp record){
        return empMapper.insert(record);
    }
    public int updateByPrimaryKey(Emp record){
        return empMapper.updateByPrimaryKey(record);
    }
    public int deleteByPrimaryKey(Integer empno){
        return empMapper.deleteByPrimaryKey(empno);
    }


}
