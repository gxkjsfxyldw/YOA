package com.server.controller;
import com.server.dao.pojo.Admin;
import com.server.service.AdminService;
import com.server.vo.common.RespBean;
import com.server.vo.param.AdminLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@Api(tags = "LoginController")
public class LoginController {

    @Autowired
    AdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLogin adminLogin, HttpServletRequest request){
        return adminService.login(adminLogin.getUsername(),adminLogin.getPassword(),adminLogin.getCode(),request);
    }
    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin geAdmininfo(Principal principal){
        //由于使用sercurity设置了当前登录的对象到全局里面去了，所以这里可以直接使用Principal获取即可
        if(principal==null){
            return null;
        }
        String username=principal.getName();
       Admin admin= adminService.getAdminByUsername(username);
       admin.setPassword(null);
       admin.setRoles(adminService.getRoles(admin.getId()));
       return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/loginout")
    public RespBean loginout( ){
        return RespBean.success("注销成功！");
    }


}
