package com.mh.sys.controller;

import com.mh.sys.service.SysUserService;
import com.mh.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xukh
 * @date 2019/12/3 0003 10:52
 */
@Controller
public class HomeController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    //登录页面
    @GetMapping({"login", "/"})
    public String login() {
        System.out.println("login");
        return "/sys/login";
    }

    //登录
    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password) {
        System.out.println(sysUserService.login(username, password));
        return sysUserService.login(username, password);
    }

    //登录主页
    @RequestMapping("/index")
    public String main() {
        return "index";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout() {
        ServletUtil.getSession().invalidate();
        return "redirect:/sys/login";
    }


}
