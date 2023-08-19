package com.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.server.dao.pojo.Employee;
import com.server.dao.pojo.Salary;
import com.server.service.EmployeeService;
import com.server.service.SalaryService;
import com.server.vo.common.RespBean;
import com.server.vo.common.RespPageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工工资账套
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工工资账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid){
        if(employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
