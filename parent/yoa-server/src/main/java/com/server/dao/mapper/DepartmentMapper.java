package com.server.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.dao.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

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
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * * 获取所有部门
     * @return
     */
    List<Department> getAllDepartment(Integer partnetId);
    /**
     * 添加部门
     * @param dep
     * @return
     */
    void addDep(Department dep);
    //删除部门
    void deleteDep(Department department);
}
