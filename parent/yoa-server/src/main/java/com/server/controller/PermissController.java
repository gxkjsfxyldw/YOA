package com.server.controller;

import com.server.dao.pojo.Role;
import com.server.service.RoleService;
import com.server.vo.common.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限组
 * @author ldw
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "添加所有角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        //先判断是否符合security的定义  ROLE_开头
        if(role.getName().startsWith("ROLE_")){
            //不符合的话手动加上
            role.setName("ROLE_"+role.getName());
        }
        if(roleService.save(role)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid){
        if(roleService.removeById(rid)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


}
