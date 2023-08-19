package com.server.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.server.dao.pojo.MailLog;
import com.server.service.MailLogService;
import com.server.utils.MailConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class RabbitMQConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    //日志记录
    private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQConfig.class);
    @Autowired
    private MailLogService mailLogService;
    //队列
    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }
    //交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }
    //绑定队列和交换机
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        //里面需要连接工厂
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        /**
         * 消息确认回调，确认消息是否到达broker
         * data：消息唯一标识
         * ack：确认结果
         * cause：失败原因
         * 这三个参数是固定的
         */
        rabbitTemplate.setConfirmCallback((data,ack,cause)->{
            String msgId = data.getId();
            //如果消息确认被消费,更新数据库
            if(ack){
                LOGGER.info("{}============>消息发送成功", msgId);
                //写入数据库
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 1).eq("msgId", msgId));
            }else {
                LOGGER.error("{}============>消息发送失败", msgId);
            }

        });
        /**
         * 消息失败回调，比如router不到queue时回调
         * msg:消息主题
         * repCode :响应码
         * repText:相应描述
         * exchange :交换机
         * routingkey:路由键
         */
        rabbitTemplate.setReturnCallback((msg,repCode,repText,exchange,routingkey)->{
            LOGGER.error("{}============>消息发送失败", msg.getBody());

        });
        return rabbitTemplate;
    }
}
