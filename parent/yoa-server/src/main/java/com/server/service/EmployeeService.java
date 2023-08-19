package com.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.server.dao.pojo.Employee;
import com.server.vo.common.RespBean;
import com.server.vo.common.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  员工管理
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * *获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * * 获取工号
     * @return
     */
    RespBean maxWorkID();

    /**
     * * 添加员工
     * @param employee
     * @return
     */
    RespBean addEmp(Employee employee);

    /**
     * * 根据id查询员工
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);

    /**
     * * 获取所有员工工资账套
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
