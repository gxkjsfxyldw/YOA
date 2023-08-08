package com.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.SalaryMapper;
import com.server.dao.pojo.Salary;
import com.server.service.SalaryService;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {

}
