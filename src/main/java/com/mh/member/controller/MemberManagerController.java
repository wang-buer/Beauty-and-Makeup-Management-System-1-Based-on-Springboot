package com.mh.member.controller;

import com.mh.member.entity.*;
import com.mh.member.service.MemberMangerService;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import com.mh.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/16 19:21
 * 会员管理
 */
@Controller
@RequestMapping("member")
public class MemberManagerController {
    @Autowired
    private MemberMangerService memberMangerService;

    //会员管理页面
    @RequestMapping("memberManagerPage")
    public String memberManagerPage() {
        return "/member/memberManagerPage";
    }

    @RequestMapping("pageQuery")
    @ResponseBody
    public LayuiTableResult pageQuery(Customer customer, PageBean pageBean) {
        LayuiTableResult layuiTableResult = memberMangerService.pageQuery(customer, pageBean);
        return layuiTableResult;
    }

    @RequestMapping("addMemberPage")
    public String addMemberPage(Model model) {
        //加载会员类型
        List<Vip> vips = memberMangerService.vipTypeList(null);
        if (vips != null) {
            model.addAttribute("vip", vips);
        }
        return "/member/addMemberPage";
    }

    //添加顾客
    @PostMapping(value = "addCustomer", produces = "application/json;charsetset=UTF-8")
    @ResponseBody
    public Result addCustomer(@RequestBody Customer customer, HttpSession session) {
        Emp user = SessionUtil.getUser(session);
        //增加顾客信息
        Result result = memberMangerService.addCustomer(customer, user);

        return result;
    }

    //删除顾客
    @RequestMapping("/delCustomerInfo/{id}")
    @ResponseBody
    public Result delCustomerInfo(@PathVariable("id") Integer id) {
        Result result = memberMangerService.delCustomerInfo(id);
        return result;
    }

    @RequestMapping("updatePage/{id}")
    public String updatePage(@PathVariable("id") Integer id, Model model, HttpSession session) {
        Customer customer = memberMangerService.getCustomerById(id, session);
        System.out.println(customer);
        model.addAttribute("customer", customer);
        return "member/updateMemberPage";
    }

    @RequestMapping("operating/{id}")
    @ResponseBody
    public Result operating(@PathVariable("id") Integer id, Integer cid) {
        Result result = memberMangerService.operating(id, cid);
        return result;
    }

    @RequestMapping(value = "updateCustomer", produces = "application/json;charsetset=UTF-8")
    @ResponseBody
    public Result updateCustomer(@RequestBody Customer customer, HttpSession session) {

        Result result = memberMangerService.updateCustomer(customer, session);
        return result;
    }

    @RequestMapping("infoPage/{id}")
    public String infoPage(HttpSession session, Model model, @PathVariable("id") Integer id) {
        Customer customer = memberMangerService.getCustomerById(id, session);

        //会员类型信息
        List<Cuspay> cuspays = memberMangerService.getCustomerInfo(id);
        //套餐信息
        List<Vpackage> vpackages = memberMangerService.getPackageInfo(id);
        vpackages.forEach(System.out::println);
        System.out.println(customer);
        System.out.println(cuspays);
        model.addAttribute("customer", customer);
        model.addAttribute("cuspays", cuspays);
        model.addAttribute("vpackages", vpackages);
        return "member/infoMemberPage";
    }

    // 考勤页面
    @RequestMapping("checkListPage/{id}")
    public String checkListPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("cid", id);
        return "member/checkListPage";
    }

    @RequestMapping("checkList/{id}")
    @ResponseBody
    public LayuiTableResult checkList(@PathVariable("id") Integer id, PageBean pageBean) {

        LayuiTableResult result = memberMangerService.getCustomerattendanceList(id, pageBean);

        return result;
    }

    //购买
    @RequestMapping("buy/{id}")
    public String buy(@PathVariable("id") Integer id, Model model, HttpSession session) {
        //1. 办卡
        //顾客
        Customer customer = memberMangerService.getCustomerById(id, session);
        //加载会员类型
        List<Vip> vips = memberMangerService.vipTypeList(null);
        if (vips != null) {
            model.addAttribute("vip", vips);
        }
        model.addAttribute("customer", customer);
        //2. 购买套餐
        List<Vpackage> vpackages = memberMangerService.getPackageList();
        vpackages.forEach(System.out::println);
        model.addAttribute("packages", vpackages);
        //3. 购买产品
        List<ProTypes> produces = memberMangerService.getProduceList();
        model.addAttribute("proType", produces);

        return "member/buy";
    }

    /*------------------购买提交的异步请求-------------------------*/
    // 1.添加会员卡
    @PostMapping(value = "addCuspay", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result addCuspay(@RequestBody Customer customer, HttpSession session) {
        Result result = memberMangerService.addCuspay(customer, session);
        return result;
    }

    // 2.添加套餐
    @PostMapping(value = "addCuspayList")
    @ResponseBody
    public Result addCuspayList(@RequestBody() CuspayArry[] array) {
        Result result = memberMangerService.addCuspayList(array);
        return result;
    }

    //3.添加产品
    @PostMapping(value = "addProduce",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result addProduce(@RequestBody() CuspayArry[] cuspayArries) {
        Result result = memberMangerService.addProduce(cuspayArries);
        return result;
    }

    /*---------------------签到和未签到------------------------------------*/
    //未签到
    @PostMapping("sign/{cid}/{uid}")
    @ResponseBody
    public Result sign(@PathVariable("cid") Integer cid,@PathVariable("uid")Integer uid, Integer isin, String whyNo) {
        System.out.println(cid + "," + isin + "," + whyNo+","+uid);
       return memberMangerService.sign(uid,cid,isin,whyNo);
    }

    //签到
    @PostMapping("signIn/{id}")
    @ResponseBody
    public LayuiTableResult signIn(@PathVariable("id") Integer id){
            //根据id查询最近签到的一条数据
        LayuiTableResult result = memberMangerService.selectCheckingByid(id);
        System.out.println(result.getData());
        return  result;
    }


    @PostMapping("saveSignIn")
    @ResponseBody
    public Result saveSignIn(Customerattendance customerattendance) {
        Result result=memberMangerService.saveSignIn(customerattendance);
        return result;
    }
}
