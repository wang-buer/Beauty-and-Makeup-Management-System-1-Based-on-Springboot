package com.mh.store.controller;

import com.mh.product.entity.CusPayL;
import com.mh.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("store")
public class StoreController {
    @Autowired
    StoreService ss;
    @RequestMapping("storeIn")
    public String In(Model m){
        CusPayL inPrevMonth   = ss.getInPrevMonth();
        CusPayL getInprevWeek =ss.getInprevWeek();
        CusPayL getInPrevYear =ss.getInPrevYear();
        CusPayL getInThisMonth= ss.getInThisMonth();
        CusPayL getInThisWeek =ss.getInThisWeek();
        CusPayL getInThisYear =ss.getInThisYear();
        CusPayL getInToday    = ss.getInToday();
        CusPayL getPrevSeason =ss.getPrevSeason();
        CusPayL getThisSeason =ss.getThisSeason();
        m.addAttribute("pmonth",inPrevMonth);
        m.addAttribute("pweek",getInprevWeek);
        m.addAttribute("pyear",getInPrevYear);
        m.addAttribute("tmonth",getInThisMonth);
        m.addAttribute("tweek",getInThisWeek);
        m.addAttribute("tyear",getInThisYear);
        m.addAttribute("today",getInToday);
        m.addAttribute("pseason",getPrevSeason);
        m.addAttribute("tseason",getThisSeason);
        return "store/storeIn";
    }
}
