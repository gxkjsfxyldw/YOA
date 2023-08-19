package com.server.utils;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.server.dao.pojo.Employee;
import com.server.dao.pojo.MailLog;
import com.server.service.EmployeeService;
import com.server.service.MailLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件定时发送任务
 */
@Component
@Slf4j
public class MailTask {

    @Autowired
    private MailLogService mailLogService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 邮件发送定时任务
     * 10秒执行一次
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void mailTask(){
//        System.out.println("执行");
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>()
                .eq("status", 0)
                .lt("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {
            //如果重试次数超过3次，更新状态为投递失败，不再重试
            if (3 <= mailLog.getCount()) {
                mailLogService.update(new UpdateWrapper<MailLog>()
                        .set("status", 2)
                        .eq("msgId", mailLog.getMsgId()));

            }
            //更新落库数据库
            mailLogService.update(new UpdateWrapper<MailLog>()
                    .set("count", mailLog.getCount() + 1)
                    .set("updateTime", LocalDateTime.now())
                    .set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                    .eq("msgId", mailLog.getMsgId()));
            //再次发送消息
            Employee emp = employeeService.getEmployee(mailLog.getEid()).get(0);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY_NAME,
                    emp,
                    new CorrelationData(mailLog.getMsgId()));
        });
    }

}
