package com.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.server.dao.pojo.Department;
import com.server.vo.common.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
public interface DepartmentService extends IService<Department> {
    /**
     * * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param dep
     * @return
     */
    RespBean addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);
}
