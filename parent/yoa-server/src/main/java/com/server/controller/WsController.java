package com.server.controller;
import com.server.dao.pojo.Admin;
import com.server.vo.param.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

/**
 * Websocket在线聊天
 */

@Controller
@EnableWebSocketMessageBroker
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg){
        //获取登录用户
        Admin admin = (Admin) authentication.getPrincipal();
        //设置当前发送用户
        chatMsg.setFrom(admin.getUsername());
        //设置当前发送对象名称
        chatMsg.setFromNickName(admin.getName());
        //发送的数据
        chatMsg.setDate(LocalDateTime.now());
        //消息中转到chatMsg.getTo()这个用户，路径为/queue/chat，消息为chatMsg
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);
    }
}
