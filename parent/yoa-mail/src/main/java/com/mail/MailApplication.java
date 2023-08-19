package com.mail;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 *
 * @version 1.0
 * @description: 启动类
 * 忽略数据库连接 exclude = {DataSourceAutoConfiguration.class}
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableRabbit
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class);
    }
    //消息队列
    @Bean
    public Queue queue() {
        return new Queue("mail.welcome");
    }

}

