package com.mh.vip.controller;

import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.service.PackageService;
import com.mh.product.entity.CusPayL;
import com.mh.sys.controller.BaseController;
import com.mh.vip.entity.Vip_ljl;
import com.mh.vip.service.Vservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("vip")
public class VipController_ljl extends BaseController {
    @Autowired
    Vservice v;
    @Autowired
    PackageService vs;
    @RequestMapping("vipselect")
    public String vipselect(Integer id,Model m){

        Vip_ljl getonevip = v.getonevip(id);
        m.addAttribute("onev",getonevip);
        return "vip/vipselect";
    }
    @RequestMapping("viplist")
    public String selVipList(Model m){
        List<Vip_ljl> vipList = v.getVipList();
        m.addAttribute("vipList",vipList);
        return "vip/viplist";
    }
    @RequestMapping("vipadd")
    public String vipadd(Model m){
        List<Package> packageList = vs.getPackageList_withStatus();

        m.addAttribute("packageList",packageList);
        return "vip/vipadd";
    }
    @RequestMapping("vipsave")
    public String vipsave(Vip_ljl vc,Integer vid){


            vc.setCreateTime(new Date());
            vc.setModifyDate(new Date());
            v.addvip(vc);
            return "redirect:/vip/viplist";

    }
    @RequestMapping("vipmfy")
    public String vipmfy(Integer id,Model m){
        Vip_ljl getonevip = v.getonevip(id);
        List<Package> packageList = vs.getPackageList_withStatus();
        m.addAttribute("packageList",packageList);
        m.addAttribute("onevip",getonevip);
        return "vip/vipmfy";
    }
    @RequestMapping("vipmfysave")
    public String vipmfysave(Vip_ljl vs){
        vs.setModifyDate(new Date());
        System.out.println(vs);
        v.mfyvip(vs);
        return "redirect:/vip/viplist";
    }
    @RequestMapping("vipdel")
    @ResponseBody
    public String vipdel(Integer vid){
        int i = v.delvip(vid);
        String res="";
        if(i>0){
            res="true";
        }else if(i==0){
            res="false";
        }else{
            res="error";
        }
        return res;
    }
    @RequestMapping("vipReport")
    public String vipReport(Model m,String year,String month,String prevMonth){
        if(year==""){
            year=null;
        }
        if(month==""){
            month=null;
        }
        if(prevMonth==""){
            prevMonth=null;
        }
        Integer userid = getUserid();
        List<CusPayL> ssss = v.vipReport("year", month, prevMonth, userid);
        m.addAttribute("info",ssss);
        return "vip/vipReport";
    }
}
