package com.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.server.dao.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
public interface MenuService extends IService<Menu> {
    /**
     * * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<Menu> getMenusWithRole();
    /**
     * @return
     * @description: 查询所有菜单
     * @param:
     */
    List<Menu> getAllMenus();
}
