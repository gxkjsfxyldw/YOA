package com.yoa.service.impl;

import com.yoa.pojo.Employee;
import com.yoa.mapper.EmployeeMapper;
import com.yoa.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
