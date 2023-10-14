package com.mh.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统配置
 *
 * @Author MH
 * @Date 2020/1/13 11:44
 */
@Controller
@RequestMapping("sys")
public class SystemSettingsController {
    @RequestMapping("/sysSetting")
    public String sysSetting() {
        return "sys/systemConfiguration";
    }
}
