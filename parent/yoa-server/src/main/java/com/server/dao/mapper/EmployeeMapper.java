package com.server.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.server.dao.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    // 获取所有员工（分页）
    IPage<Employee> getEmployeeByPage(@Param("page") Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);
    //根据id查询员工
    List<Employee> getEmployee(Integer id);
    //获取所有员工工资账套
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
