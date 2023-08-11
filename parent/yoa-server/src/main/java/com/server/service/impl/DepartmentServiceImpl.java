package com.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.dao.mapper.DepartmentMapper;
import com.server.dao.pojo.Department;
import com.server.service.DepartmentService;
import com.server.vo.common.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

   @Autowired
   private DepartmentMapper departmentMapper;

    /**
     * * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        //从顶级开始递归往下查
        return departmentMapper.getAllDepartment(-1);
    }
    /**
     * 添加部门
     * @param dep
     * @return
     */
    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()) {
            return RespBean.success("添加成功！", dep);
        }
        return RespBean.error("添加失败！");
    }
    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department department=new Department();
        department.setId(id);
        departmentMapper.deleteDep(department);
        if(-2==department.getResult()){
            return RespBean.error("该部门下还有子部门，删除失败");
        }
        if(-1==department.getResult()){
            return RespBean.error("该部门下还有子部门，删除失败");
        }
        if(1==department.getResult()){
            return RespBean.success("删除成功");
        }
        return RespBean.error("未知错误，删除失败");
    }
}
