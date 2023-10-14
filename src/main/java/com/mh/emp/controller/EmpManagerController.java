package com.mh.emp.controller;

import com.mh.emp.entity.EmpManager;
import com.mh.emp.entity.Emprattendance;
import com.mh.emp.service.EmpManagerService;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import com.mh.util.SessionUtil;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/13 12:01
 * 员工管理
 */
@Controller
@RequestMapping("emp")
public class EmpManagerController {
    @Autowired
    private EmpManagerService empManagerService;
    //去员工管理页面
    @RequestMapping("empManagerPage")
    public String empManagerPage(Model model, HttpSession session){
        //查询有没有签到
        Emp user = SessionUtil.getUser(session);
        Result result=empManagerService.getEmpRattendance(user);
        System.out.println(result);
        model.addAttribute("result",result);
        return "emp/empManagerPage";
    }

    //页面加载获取员工数据
    @RequestMapping("pageQuery")
    @ResponseBody
    public LayuiTableResult pageQuery(String empname, String emptype, PageBean pageBean) {
        LayuiTableResult layuiTableResult = empManagerService.getEmps(empname, emptype, pageBean);
        return layuiTableResult;
    }
    //签到
    @RequestMapping("sign")
    @ResponseBody
    public Result sign(Emprattendance emprattendance){
        Result result= empManagerService.signIn(emprattendance);
        return  result;
    }

    //考勤列表
    @RequestMapping("signListPage/{id}")
    public String signListPage(Model model,@PathVariable("id") Integer id,EmpManager empManager) {
        model.addAttribute("empid",id);
        model.addAttribute("empManager",empManager);
        return "/emp/signListPage";
    }

    //考勤列表
    @RequestMapping("signList")
    @ResponseBody
    public LayuiTableResult signList(Emprattendance emprattendance,PageBean pageBean) {
        LayuiTableResult layuiTableResult=empManagerService.signList(emprattendance,pageBean);
        return layuiTableResult;
    }

}
