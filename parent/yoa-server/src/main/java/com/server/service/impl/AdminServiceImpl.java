package com.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.config.Sercurity.component.JwtTokenUtils;
import com.server.dao.mapper.AdminMapper;
import com.server.dao.mapper.RoleMapper;
import com.server.dao.pojo.Admin;
import com.server.dao.pojo.Role;
import com.server.service.AdminService;
import com.server.vo.common.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;//这个sercurity提供的加密方法
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")//从配置文件里面获取值
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    /**
     *登录之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password,String code, HttpServletRequest request) {
        //从session里面获取到验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        //判断验证码是否为空，比较传入进来的验证码是否和session里面的一致
        if(StringUtils.isEmpty(captcha)||!captcha.equalsIgnoreCase(captcha)){
            return RespBean.error("验证码错误");
        }
        //获取登录用户对象
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        //判断用户名是否为空，然后通过加密之后的密码进行匹配是否一致
        //如果密码匹配，则返回 true；如果不匹配，则返回 false。 第一个参数表示需要被解析的密码。第二个参数表示存储的密码
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确噢");
        }
        //如果数据库禁用了，也不能登录
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }
        //更新security登录用户对象
        //第一个参数是userDetails
        //第二个参数是密码，不用
        //第三个是权限，权限列表
        System.out.println("userDetails1: "+userDetails);//
        System.out.println("userDetails2: "+userDetails.getAuthorities());//null
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
//        System.out.println("authenticationToken:  "+authenticationToken.getAuthorities());  根本没存储进去
        //拿到这个token就存放在springSercurity里面去
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //获取token
        String token= jwtTokenUtils.generateToken(userDetails);
        //新建map，存放token和头部信息返回
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功！",tokenMap);
    }

    /**
     * * 根据登录用户名，查询用户信息
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }
    /**
     * * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {

        return roleMapper.getRoles(adminId);
    }

}
