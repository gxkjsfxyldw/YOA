package com.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.server.dao.pojo.Employee;
import com.server.vo.common.RespPageBean;

import java.time.LocalDate;

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
}
