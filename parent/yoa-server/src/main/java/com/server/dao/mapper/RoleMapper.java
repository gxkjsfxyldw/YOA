package com.server.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.dao.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    //根据用户id查询角色列表
    List<Role> getRoles(Integer adminId);
}
