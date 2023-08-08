package com.server.config.Sercurity.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限控制
 * 判断用户角色权限
 * @author ldw
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            //当前url所需角色
            String needRole = configAttribute.getAttribute();
            //判断角色是否登录即可访问的角色，此角色在CustomFilter中设置  之前自己设置的ROLE_LOGIN
            if ("ROLE_LOGIN".equals(needRole)) {
                //判断是否登录  instanceof用于检查一个对象是否是某个类的实例
                //如果 authentication 是 AnonymousAuthenticationToken 的实例，条件就会为真，这意味着用户是一个匿名用户
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //在 Spring Security 中，用户登录后，
                    // authentication 对象通常是 UsernamePasswordAuthenticationToken 类的实例。
                    // 这个类表示已经通过用户名密码认证的用户信息。
                    throw new AccessDeniedException("尚未登录，请登录！");
                } else {
                    return;
                }
            }
            //判断用户角色是否为url所需角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
