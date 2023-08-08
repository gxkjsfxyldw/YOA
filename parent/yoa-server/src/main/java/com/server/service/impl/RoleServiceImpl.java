package com.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.RoleMapper;
import com.server.dao.pojo.Role;
import com.server.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
