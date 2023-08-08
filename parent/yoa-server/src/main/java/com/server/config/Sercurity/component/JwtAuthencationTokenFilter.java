package com.server.config.Sercurity.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * @author ldw
 */
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")//从配置文件里面获取值
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 前置拦截
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String ahtuheader = httpServletRequest.getHeader(tokenHeader);
        //存在token
        if(ahtuheader!=null && ahtuheader.startsWith(tokenHead)){
            String authtoken = ahtuheader.substring(tokenHead.length());
            String userNameFromToken = jwtTokenUtil.getUserNameFromToken(authtoken);
            //token存在用户名但未登录, 在sercuirty全局对象上下文中找不到token
            if(null != userNameFromToken && null == SecurityContextHolder.getContext().getAuthentication()){
                //登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);//这个就是重写过的那个
                //验证token是否有效，重新设置用户对象
                //验证 JWT，如果 JWT 有效，则创建一个经过身份验证的用户信息对象并设置到 Spring Security 的上下文中，
                // 以便后续的请求能够通过身份验证。这样，用户就可以在系统中访问受保护的资源。
                if(jwtTokenUtil.validateToken(authtoken,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    //这一行将请求的详细信息（httpServletRequest）设置到 authenticationToken 中
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    //把登录对象设置到全局对象里面,这样后续的请求就会被标记为已通过身份验证的用户，从而允许用户访问受保护的资源
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
