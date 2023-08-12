package com.server.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.dao.pojo.AdminRole;
import com.server.vo.common.RespBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    //更新操作员角色
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids")Integer[] rids);
}
