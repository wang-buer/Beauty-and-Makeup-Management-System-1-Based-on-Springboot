package com.mh.product.controller;

import com.mh.product.entity.CusPayL;
import com.mh.product.entity.IntoGoods;
import com.mh.product.entity.ProType;
import com.mh.product.entity.Produce;
import com.mh.product.service.ProService;
import com.mh.sys.controller.BaseController;
import com.mh.sys.entity.Emp;
import com.mh.util.PageInfo;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("pro")
public class ProController extends BaseController {
    @Autowired
    ProService ps;

    @RequestMapping("truncate_inwork")
    public String truncate_inwork(){
        ps.truncate();
        return "pro/PurchaseRecords.html";
    }

    //产品分类
    @RequestMapping("protype")
    public String getProType(Model m,Integer curr,Integer pageSize){
        if(pageSize==null){ pageSize=5; }
        if(curr==null){ curr=0; }
        curr++;
        PageInfo p=new PageInfo((curr-1)*pageSize,pageSize);
        p.setPageSize(pageSize);
        p.setTotalpagecount(ps.getPtCount());
        List<ProType> protypeList = ps.getProList(null,p);
        p.setCurr(curr);
        m.addAttribute(protypeList);
        m.addAttribute(p);
        return "pro/protype";
    }
    //产品分类
    @RequestMapping("protype1")
    public String getProType1(Model m,Integer curr,Integer pageSize){
        if(pageSize==null){ pageSize=5; }
        if(curr==null){ curr=0; }

        PageInfo p=new PageInfo((curr-1)*pageSize,pageSize);

        List<ProType> protypeList = ps.getProList(null,p);
        p.setCurr(curr);
        m.addAttribute(protypeList);
        m.addAttribute(p);
        return "pro/protype";
    }
    @RequestMapping("topromdfy")
    public String toProTypeMdfyPage(Integer id,Model m){
        ProType proTypeById = ps.getProTypeById(id);
        m.addAttribute("prot",proTypeById);
        return "pro/protmfy";
    }//修改页面

    @RequestMapping("mdfpt")
    public String mdfpt(Model m,ProType p){
        ps.updataById(p);
        return "redirect:/pro/protype";
    }//修改功能
    @RequestMapping("del")
    public String delProType(Integer id ){
        if(id!=null||!"".equals(id)||id!=0){
            ps.delById(id);
        }
        return "redirect:/pro/protype";
    }

    @RequestMapping("toadd")
    public String addpage(){
        return "pro/proadd";
    }

    @RequestMapping("addsave")
    public String addsave(ProType p){
        ps.addProType(p);
        return "redirect:/pro/protype";
    }

    //删除选中的软件
    @RequestMapping(value="delall")
    public String delAppList( String ids){
        String[] appid = ids.split(",");
        for (String id : appid) {
            int	aid=Integer.parseInt(id);
            int res1=ps.delById(aid);
        }
        return "redirect:/pro/protype";
    }

    //添加修改产品类型时的验证>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @RequestMapping(value = "protypeValid",produces="application/json")
    @ResponseBody
    public String VtName(String name){

        String res="";
        if(ps.vTypeName(name)<=0){
            res="true";
            System.out.println(true);
        }else{
            System.out.println(false);
            res="false";
        }

        return res;
    }



    /*产品*//*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @RequestMapping("producePage")
    public  String toProduceList(Model m,String produceName,Integer curr,Integer pageSize){
        if(pageSize==null){
            pageSize=7;
        }
        if(curr==null){
            curr=1;
        }
        if(produceName==null){
            produceName=null;
        }
        if("".equals(produceName)){
            produceName=null;
        }
        int produceCount = ps.getProduceCount(produceName);
        PageInfo p=new PageInfo((curr-1)*pageSize,pageSize);
        p.setPageSize(pageSize);
        p.setCurr(curr);
        p.setTotal((long) produceCount);
        p.setTotalpagecount(produceCount);
        List<Produce> produceList = ps.getProduceList(produceName, p);
        if(produceList.size()==0){
             produceList = ps.getProduceList(null, p);
        }
        m.addAttribute("proname",produceName);//查询数据回显
        m.addAttribute(p);//添加分页数据
        m.addAttribute("produceList",produceList);
        return "pro/producePage";
    }

    @RequestMapping("addproductPage")
    public String addpropage(Model m){

        List<ProType> proList = ps.getProList1();
        System.out.println(proList);
        m.addAttribute(proList);
        return "pro/produceadd";
    }

    @RequestMapping(value = "verifyProName",produces="application/json")
    @ResponseBody
    public String verifyProName(String name){
        int a=ps.verfiyProduceName(name);
        System.out.println(a+"条数据");
        String res=null;
        if(a<=0){
           res="true";
            System.out.println(true);
        }else if(a==1){
            res="false";
            System.out.println(false);
        }else{
            System.out.println("error");
            return "error";
        }
        return res;
    }



    @RequestMapping("addproducesave")
    public String addsave(Model m,Produce p){
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
        System.out.println(p);
            int a=ps.addproduce(p);
            String res=null;;
            if(a>0){
                System.out.println("t");
            }else{
                System.out.println("f");
            }
            return "redirect:/pro/producePage";
    }
    @RequestMapping("produceModifyPage")
    public String producemodifypage(Integer pid,Model m){
        Produce produce = ps.getOneProduceById(pid);
        List<ProType> proList = ps.getProList1();
        m.addAttribute(produce);
        m.addAttribute(proList);
        return "pro/producemodify";
    }

    @RequestMapping("proselect_page")
    public String proselect_page(Integer pid,Model m){
        Produce produce = ps.getOneProduceById(pid);
        List<ProType> proList = ps.getProList1();
        m.addAttribute(produce);
        m.addAttribute(proList);
        return "pro/proselect_page";
    }
    @RequestMapping("modifyproducesave")
    public String modifyproducesave(Produce p,Model m){
        int i = ps.updateProduceById(p);
        return "redirect:/pro/producePage";
    }

    @RequestMapping("produce_delete")
    public String produce_delete(Integer pid,Model m){
        ps.delproduceById(pid);
        return "redirect:/pro/producePage";
    }


    /**销售报表 查所有员工的产品 项目 等等消费报表*/
    @RequestMapping("storeReport")
    public String storeReport(Model m,String year,String month,String prevMonth) throws NoSuchFieldException, IllegalAccessException {
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
        List<CusPayL> info = ps.getInfo(userid,year,month,prevMonth);
        Double monthPackage = ps.getMonthPackage(userid, year, month, prevMonth);
        Double monthProduce = ps.getMonthProduce(userid, year, month, prevMonth);
        Double monthVip = ps.getMonthVip(userid, year, month, prevMonth);
        m.addAttribute("info",info);
        m.addAttribute("monthPackage",monthPackage);
        m.addAttribute("monthProduce",monthProduce);
        m.addAttribute("monthVip",monthVip);
        return "pro/storeReport" ;
    }
    /**销售报表 产品 个人*/
    @RequestMapping("proReport")
    public String proReport(Model m,String year,String month,String prevMonth)  {
        if(year==""){
            year=null;
        }
        if(month==""){
            month=null;
        }
        if(prevMonth==""){
            prevMonth=null;
        }

        List<CusPayL> proReport = ps.getProReport(year,month,prevMonth,null);
        m.addAttribute("proReport",proReport);
        return "pro/proReport" ;
    }
    @RequestMapping("producesize")
    public String ps(Model m,Integer curr,String produceName,Integer pageSize){
       if(curr==null) {
           curr=1;
       }
       if(pageSize==null){
           pageSize=10;
       }
        PageInfo p=new PageInfo((curr-1)*pageSize,pageSize);
        int produceCount = ps.getProduceCount(produceName);
        p.setPageSize(pageSize);
        p.setCurr(curr);
        p.setTotal((long) produceCount);
        p.setTotalpagecount(produceCount);
        List<Produce> produceList = ps.getProduceList(produceName, p);
        m.addAttribute("proname",produceName);//查询数据回显
        m.addAttribute(p);//添加分页数据
        m.addAttribute("produceList",produceList);
        return "pro/producesize";
    }
    /*进货 ：修改产品数量*/
    @RequestMapping("updataProduceNum")
    public String updateP_Num(Model m,Integer num,Integer id,Integer innum){
      /*  Date date = new Date();//进货时间*/
        Integer userid = getUserid();//进货人
        Integer num1=num;//数量
        Integer pid=id;//商品id
        ps.updatepronumber(id,num1);
        IntoGoods intoGoods = new IntoGoods();
        intoGoods.setEmpid(userid);
        intoGoods.setIntotime(LocalDateTime.now());
        intoGoods.setNum(innum);//进货数量
        intoGoods.setPid(pid);
        ps.addIntoBook(intoGoods);
        return "redirect:/pro/producesize";
    }
    //查看进货记录
    @RequestMapping("purchaseRecords")
    public String PurchaseRecords(Model m,Integer pid){
        List<IntoGoods> proIntoBook = ps.getProIntoBook(pid);
        m.addAttribute("proIntoBook",proIntoBook);
        List<Produce> produces = ps.getproduceList_option();
        m.addAttribute("produceList",produces);
        return "pro/PurchaseRecords";
    }

}
