package com.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.DepartmentMapper;
import com.server.dao.pojo.Department;
import com.server.service.DepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
