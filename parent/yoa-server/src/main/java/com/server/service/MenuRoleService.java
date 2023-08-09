package com.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.server.dao.pojo.MenuRole;
import com.server.vo.common.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
public interface MenuRoleService extends IService<MenuRole> {
    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
