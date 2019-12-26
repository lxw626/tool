package com.origin.tool.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.tool.entity.Emp;
import com.origin.tool.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lixiewen
 * @create 2019-11-27 15:41
 */
@RestController
public class EmpController {
    @Autowired
    EmpService empService;

    @PostMapping("/emp")
    public int addEmp(@RequestBody Emp emp){
        System.out.println("addEmp:"+emp);
        return empService.insert(emp);
    }

    @DeleteMapping("/emp/{empno}")
    public int deleteEmpByEmpno(@PathVariable("empno") Integer empno){
        System.out.println(empno);
        return empService.deleteByPrimaryKey(empno);
    }

    @PutMapping("/emp")
    public int updateEmpByEmpno(@RequestBody Emp emp){
        System.out.println("updateEmpByEmpno:"+emp);
        return empService.updateByPrimaryKey(emp);
    }
    @PostMapping("/getEmpsBypage")
    public PageInfo<Emp> getEmpsBypage(@RequestBody Emp params){
        Integer currentPage = params.getCurrentPage();
        Integer pageSize = params.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        List<Emp> emps = empService.getEmps(params);
        return new PageInfo<>(emps);
    }
    @GetMapping("/getStatistics")
    public Map<String,List<String>> getStatistics(){
        List<String> jobs = empService.jobs();
        List<String> deptnos = empService.deptnos();
        Map<String,List<String>> result = new HashMap<>();
        result.put("jobs",jobs);
        result.put("deptnos",deptnos);
        return result;
    }

}
