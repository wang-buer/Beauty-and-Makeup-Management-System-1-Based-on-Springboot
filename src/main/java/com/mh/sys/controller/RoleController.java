package com.mh.sys.controller;

import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Role;
import com.mh.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/10 16:21
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("query")
    @ResponseBody
    public LayuiTableResult query(){
        return LayuiTableResult.ok(0,roleService.list());
    }

    @RequestMapping("roleManagePage")
    public String roleManagePage(){
        return "sys/roleManagerPage";
    }

    @RequestMapping("pageQuery")
    @ResponseBody
    public LayuiTableResult pageQuery(String rname){
        List<Role> list =roleService.getRoles(rname);
        return LayuiTableResult.ok(list.size(),list);
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(Role role){
       return roleService.addOrUpdate(role);
    }
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(Role role){
        return roleService.delete(role);
    }

    @RequestMapping("getRes/{rid}")
    @ResponseBody
    public LayuiTableResult getRes(@PathVariable("rid") Integer rid, PageBean pageBean) {
        LayuiTableResult result = roleService.getAllRoles(rid);
        return result;
    }
    @RequestMapping("/setRes")
    @ResponseBody
    public Result setRes(String resIds, Integer rid) {
        return roleService.setRes(resIds,rid);
    }
}
