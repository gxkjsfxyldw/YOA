package com.server.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.dao.pojo.MenuRole;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    /**
     * *更新角色菜单
     * @param rid
     * @param mids
     * 记得加PathParam注解不然会有问题
     */
    Integer insertRecord(@PathParam("rid") Integer rid, @PathParam("mids") Integer[] mids);
}
