package com.server.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.dao.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    //获取所有操作员
    List<Admin> getAlladmin(@Param("id") Integer id,@Param("keywords")  String keywords);
}
