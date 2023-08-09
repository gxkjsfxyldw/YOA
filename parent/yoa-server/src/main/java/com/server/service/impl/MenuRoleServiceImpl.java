package com.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.MenuRoleMapper;
import com.server.dao.pojo.MenuRole;
import com.server.service.MenuRoleService;
import com.server.vo.common.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private   MenuRoleMapper menuRoleMapper;


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */

    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //先把它对应的菜单先删除了
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        //然后再添加进去，这样作为更新
        if(null==mids||0== mids.length){
            return RespBean.success("更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        if(result==mids.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
