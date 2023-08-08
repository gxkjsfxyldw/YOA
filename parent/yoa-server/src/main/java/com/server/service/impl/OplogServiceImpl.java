package com.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.OplogMapper;
import com.server.dao.pojo.Oplog;
import com.server.service.OplogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements OplogService {

}
