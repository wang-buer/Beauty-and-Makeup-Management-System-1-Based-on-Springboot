package com.mh.sys.controller;

import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import com.mh.sys.service.SysUserService;
import com.mh.sys.service.UserManagerService;
import com.mh.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户管理
 *
 * @Author MH
 * @Date 2020/1/9 9:18
 */
@Controller
@RequestMapping("sys")
public class UserManagerController {
    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("userManagerPage")
    public String userManager() {
        return "sys/userManagerPage";
    }

    @RequestMapping("pageQuery")
    @ResponseBody
    public LayuiTableResult pageQuery(String empname, PageBean pageBean) {
        System.out.println(empname);
        //员工集合和总条数
        return userManagerService.getUsers(empname, pageBean);
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(Emp emp) {
        return userManagerService.addOrUpdate(emp);
    }
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(String id){
        System.out.println("id:"+id);
        return userManagerService.delete(id);
    }


    @RequestMapping("setRole")
    @ResponseBody
    public Result setRole(Integer id, Integer rid, String rname){
        System.out.println("id:"+id+",rid:"+rid+"rname："+rname);
        return  userManagerService.setRole(id,rid,rname);
    }
    @GetMapping("/userSetting")
    public String userSetting(HttpSession httpSession, Model model) {
        Emp userEntity = ShiroUtils.getUserEntity();
        System.out.println(userEntity);
        model.addAttribute("userEntity",userEntity);
        return "sys/userSetting";
    }
    @GetMapping("/updatePasswordPage")
    public String updatePasswordPage() {
        return "sys/updatePassword";
    }
    //修改密码
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result updatePassword(String oldpassword, String newpassword) {
        return sysUserService.updatePassword(oldpassword, newpassword);
    }
}
