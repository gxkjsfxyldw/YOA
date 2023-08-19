package com.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.EmployeeMapper;
import com.server.dao.mapper.MailLogMapper;
import com.server.dao.pojo.Employee;
import com.server.dao.pojo.MailLog;
import com.server.service.EmployeeService;
import com.server.utils.MailConstants;
import com.server.vo.common.RespBean;
import com.server.vo.common.RespPageBean;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  员工管理
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailLogMapper mailLogMapper;

    /**
     * *  获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page=new Page<>(currentPage,size);
        IPage<Employee> employeeByPage =employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean=new RespPageBean(employeeByPage.getTotal(),employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * * 获取工号
     * @return
     */
    @Override
    public RespBean maxWorkID() {
        //mybatis 还能带函数查的？  查workID字段的最大值，查到的值，max(workID)是key，对应的值是value
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        //将map里面的key取出来，是int类型的，进行+1，然后格式化一下int类型，默认有8位，前面不足8位的补0，再转成string类型
        String result = String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1);
        return RespBean.success(result);
    }

    /**
     * * 添加员工
     * @param employee
     * @return
     * rabbitemq 消息发送者
     */
    @Override
    @Transactional
    public RespBean addEmp(Employee employee) {
        //要单独处理一下合同期限
        //合同开始时间
        LocalDate beginContract = employee.getBeginContract();
        //合同结束时间
        LocalDate endContract = employee.getEndContract();
        //计算一下两个时间相差多少天,多少小时，多少秒
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        //现在我们要查的是两个日期相差多少年,并且保留两位小数
        DecimalFormat decimalFormat=new DecimalFormat("##.00");
        //强制转为double
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        //判断受影响的行数，1表示已经插入进去
        if (1 == employeeMapper.insert(employee)) {
            //先要处理消息落库    //数据库记录已发送的消息
            String msgId= UUID.randomUUID().toString();
            MailLog mailLog=new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);
            //发送消息
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY_NAME,
                    emp,
                    new CorrelationData(msgId));
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
    /**
     * * 根据id查询员工
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }
    /**
     * * 获取所有员工工资账套
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }
}
