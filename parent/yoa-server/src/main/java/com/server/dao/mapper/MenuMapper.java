package com.server.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.dao.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId(Integer id);
    /**
     * @return
     * @description: 根据角色获取菜单列表
     * @param:
     */
    List<Menu> getMenusWithRole();
}
