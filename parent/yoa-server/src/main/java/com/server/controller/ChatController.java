package com.server.controller;

import com.server.dao.pojo.Admin;
import com.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @description: 在线聊天
 *
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

}
