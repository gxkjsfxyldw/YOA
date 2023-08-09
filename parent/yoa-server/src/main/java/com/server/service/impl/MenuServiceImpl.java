package com.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.AdminMapper;
import com.server.dao.mapper.MenuMapper;
import com.server.dao.pojo.Admin;
import com.server.dao.pojo.Menu;
import com.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * * 根据用户id查询菜单列表
     * 后面如果有修改或删除菜单，记得要先删除redis缓存
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        Integer adminId = ((Admin) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        ValueOperations<String, Object> stringvalue = redisTemplate.opsForValue();
        //从redis中获取菜单数据，这个key是自己设置的
        List menus=(List<Menu>)stringvalue.get("menu_"+adminId);
        //判断是否存在redis中。如果为空则去数据库查询
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenuByAdminId(adminId);
            //同时将菜单数据存储到redis中
            stringvalue.set("menu_"+adminId,menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }
    /**
     * @return
     * @description: 查询所有菜单
     * @param:
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}
