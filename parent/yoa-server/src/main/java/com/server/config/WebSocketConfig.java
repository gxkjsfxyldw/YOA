package com.server.config;

import com.server.config.Sercurity.component.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;



/**
 * WebSocket配置类
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @return
     * @description: 添加这个Endpoint，这样网页可以通过WebSocket连接上服务
     * 也就是我们配置websocket的服务地址，并且可以指定是否使用socketJS
     * @param: registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1.将ws/ep路径注册为stomp的端点，用户连接了这个端点就可以进行websocket通讯，支持socketJs
         * 2.setAllowedOrigins ( "*"):允许跨域
         * 3.withSockJS():支持socketJS访同
         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * @return
     * @description: 输入通道参数配置
     * @param: registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为连接，如果是，需要获取token，并且设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    //获取token，这个Auth-Token是前端回传的，不是乱写的
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    //判断token是否为空
                    if (!StringUtils.isEmpty(token)) {
                        //拿到完整的token
                        String authToken = token.substring(tokenHead.length());
                        //获取用户名
                        String username = jwtTokenUtil.getUserNameFromToken(authToken);
                        //token中存在用户名
                        if (!StringUtils.isEmpty(username)) {
                            //登录
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            //验证Token是否有效，重新设置用户对象
                            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                                //设置用户对象
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                               //设置到sercuirty全局对象里面去
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }
    /**
     * @return
     * @description: 配置消息代理
     * @param: registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域，可以配置多个,配置代理目的地前缀为/queue，可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}
