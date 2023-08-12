package com.server.utils;

import com.server.dao.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 */

public class AdminUtils {
    /**
     * @description: 获取当前登录操作员
     * @param:
     * @return
     * 从pringsecurity中获取到当前登录用户信息，将它强制转为Admin
     */
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
