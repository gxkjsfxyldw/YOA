package com.server.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.server.dao.pojo.Admin;
import com.server.dao.pojo.Menu;
import com.server.dao.pojo.Role;
import com.server.vo.common.RespBean;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
public interface AdminService extends IService<Admin> {
    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password,String code, HttpServletRequest request);

    /**
     * * 根据用户名获取当前登录对象
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

    /**
     * * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role>getRoles(Integer adminId);
}
