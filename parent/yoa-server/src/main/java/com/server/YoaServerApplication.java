package com.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.server.dao.mapper")
//开启定时器任务
@EnableScheduling
public class YoaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoaServerApplication.class, args);
    }

}
