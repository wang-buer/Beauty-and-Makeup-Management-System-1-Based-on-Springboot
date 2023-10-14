package com.mh.package_ljl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mh.member.entity.Produces;
import com.mh.member.entity.Vip;
import com.mh.member.mapper.VipMapper;
import com.mh.model.Result;
import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.entity.PagePro;
import com.mh.package_ljl.service.PackageService;
import com.mh.package_ljl.service.PageproService;
import com.mh.product.entity.CusPayL;
import com.mh.product.entity.Produce;
import com.mh.product.service.ProService;
import com.mh.sys.controller.BaseController;
import com.mh.util.SessionUtil;
import com.mh.vip.entity.Vip_ljl;
import com.mh.vip.service.Vservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("pac")
public class PackageController extends BaseController {
    @Autowired
    PackageService ps;
    @Autowired
    ProService pss;
    @Autowired
    Vservice vss;
    @Autowired
    private PageproService pageproService;
    @Autowired
    private VipMapper vipMapper;

    @RequestMapping("paclist")
    public String paclist(Model m) {
        m.addAttribute("paclist", ps.getPackageList());
        return "pac/paclist";
    }
    @RequestMapping("package_add")
    public String pac_add(Model m) {
        List<Produce> produces = pss.getproduceList_option();
        List<Vip_ljl> vipList = vss.getVipList();
        m.addAttribute("produces",produces);
        m.addAttribute("vipList",vipList);
        return "pac/package_add";
    }
    @RequestMapping("packageaddsave")
    public String pac_add_save(Integer[] proinfo, String pacname, Integer pacprice, Integer istrue, String desci, Integer vipid) {
        Package p = new Package();
        p.setVipid(vipid);
        p.setPname(pacname);
        p.setDesci(desci);
        p.setCreateDate(new Date());
        p.setModifyDate(new Date());
        p.setIstrue(istrue);
        p.setPrice(pacprice);
        ps.addPackage(p);
        return "redirect:/pac/paclist";
    }
    @RequestMapping("package_del")
    public String  pac_del(Integer id){
        ps.delp_pac(id);
        ps.delPackage(id);
        return "redirect:/pac/paclist";
    }
    @RequestMapping("pacReport")
    public String pacreport(Model m,String year,String month,String prevMonth){
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
        List<CusPayL> info = ps.getpacReport(year,month,prevMonth,userid);
        m.addAttribute("info",info);
        return "pac/pacReport";
    }
    @RequestMapping("getproselect")
    public String getproselect(Integer pid,Model m){
        List<PagePro> pagePros = ps.getpac_pronifoList(pid);
        Package onePac = ps.getOnePac(pid);
        List<Vip_ljl> vipList = vss.getVipList();
        m.addAttribute("pagePros",pagePros);
        m.addAttribute("vipList",vipList);
        m.addAttribute("onePac",onePac);
        return "pac/pacselect";
    }

    /*
        根据套餐id查询套餐
     */
    @GetMapping("/goPackageInfoPage")
    public String goPackageInfoPage(Integer pid, Model model) {

        if (pid == null) {
            return "redirect:/pac/getproselect";
        }
        Package pack = ps.getPackage(pid);
        List<PagePro> pagePros = null;
        if (pack != null) {
            pagePros = pageproService.goPackageInfoPage(pid);
        }
//        List<Integer> collect = pagePros.stream().map(Produces::getId).collect(Collectors.toList());
        List<Produces> getlist = pageproService.getlist();
        double sums=0;

        List<Map> list = new ArrayList<>();
        for (Produces produces : getlist) {
            Map map = new HashMap();
            map.put("id",produces.getId());
            map.put("name",produces.getName());
            map.put("price",produces.getPrice());
            for (PagePro pagePro : pagePros) {
                if (produces.getId() == pagePro.getRoduceid()){
                    map.put("num",pagePro.getNum());
                    sums+=produces.getPrice()*pagePro.getNum();
                }
            }
            if (!map.containsKey("num")){
                map.put("num",0);
            }
            list.add(map);
        }
        model.addAttribute("package", pack);
//        model.addAttribute("pagePros", pagePros);
        model.addAttribute("vip", vipMapper.selectList(new QueryWrapper<>()));
        model.addAttribute("producesList",list );
        model.addAttribute("sums",sums);
        return "pac/packageInfoPage";
    }

    /*@ResponseBody
    @GetMapping("/getPro")
    public String getPro() {
        List<Produces> proList = pageproService.getlist();
        List<Map<String, String>> list = new ArrayList<>();
        for (Produces pagePro : proList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", pagePro.getName());
            map.put("value", pagePro.getId().toString());
            list.add(map);
        }
        return JSONArray.toJSONString(list);
    }*/

    //修改套餐
    @PostMapping(value = "/modifyPackage", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Result modifyPackage(@RequestBody String strJson) {
        try {
            JSONObject jsonObject = JSON.parseObject(strJson);
            List<Package> pack = JSON.parseArray(jsonObject.getString("pack"), Package.class);
            List<Produces> pro = JSON.parseArray(jsonObject.getString("pro"), Produces.class);
            ps.updatePackage(pack.get(0), pro);
            return Result.ok("更新成功！");
        } catch (Exception e) {
            return Result.error("更新失败！");
        }
    }

}
