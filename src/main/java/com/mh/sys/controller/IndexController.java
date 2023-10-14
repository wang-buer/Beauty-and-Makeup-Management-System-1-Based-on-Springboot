package com.mh.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 前端控制器
 * @create: 2019-11-28
 */
@Controller
public class IndexController {
    @GetMapping({ "/page/login-1"})
    public String loginOut() {
        return "/page/login-1";
    }

    @GetMapping({"/page/loginOut","/page/login-2"})
    public String login2() {
        return "/page/login-2";
    }

    @GetMapping("/page/userSetting")
    public String userSetting() {
        return "/page/user-setting";
    }

    @GetMapping("/page/userPassword")
    public String userPassword() {
        return "/page/user-password";
    }

    @GetMapping("/page/welcome")
    public String welcome() {
        return "/page/welcome-1";
    }

    @GetMapping("/page/welcome-1")
    public String welcome1() {
        return "/page/welcome-1";
    }

    @GetMapping("/page/welcome-2")
    public String welcome2() {
        return "/page/welcome-2";
    }

    @GetMapping("/page/setting")
    public String setting() {
        return "/page/setting";
    }

    @GetMapping("/page/table")
    public String table() {
        return "/page/table";
    }

    @GetMapping("/page/menu")
    public String menu() {
        return "/page/menu";
    }

    @GetMapping("/page/form-step")
    public String formStep() {
        return "/page/form-step";
    }

    @GetMapping("/page/form")
    public String form() {
        return "/page/form";
    }

    @GetMapping("/page/editor")
    public String editor() {
        return "/page/editor";
    }

    @GetMapping("/page/layer")
    public String layer() {
        return "/page/layer";
    }

    @GetMapping("/page/button")
    public String button() {
        return "/page/button";
    }

    @GetMapping("/page/404")
    public String err() {
        return "/sys/dtree";
    }

    @GetMapping("/page/table-select")
    public String tableSelect() {
        return "/page/table-select";
    }
}
