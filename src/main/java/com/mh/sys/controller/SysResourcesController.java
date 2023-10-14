package com.mh.sys.controller;

import com.mh.model.Menu;
import com.mh.sys.service.SysResourcesService;
import com.mh.util.TreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 菜单
 */
@RestController
@RequestMapping("/sys/resources")
public class SysResourcesController {
    @Autowired
    public SysResourcesService sysResourcesService;
    @RequestMapping("/getInitJson")
    public String getInitJson(HttpSession httpSession){
        List<Menu> menu = sysResourcesService.getMenu(httpSession);
        String json = new TreeBuilder().buildTree(menu);
        json = "{\n" +
                "  \"menuInfo\": {\n" +
                "    \"data\":" + json + "}\n" +
                "}";
        return json;
    }
}

