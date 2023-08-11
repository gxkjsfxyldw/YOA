package com.server.controller;

import com.server.dao.pojo.Department;
import com.server.service.DepartmentService;
import com.server.vo.common.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ldw
 * @since 2023-08-04
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")

    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation("添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        return departmentService.addDep(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id){
        return departmentService.deleteDep(id);
    }
}
