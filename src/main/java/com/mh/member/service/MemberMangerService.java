package com.mh.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.member.entity.*;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberMangerService extends IService<Customer> {
    LayuiTableResult pageQuery(Customer customer, PageBean pageBean);
    List<Vip> vipTypeList(Integer id);

    Result addCustomer(Customer customer, Emp user);

    Result delCustomerInfo(Integer id);

    Customer getCustomerById(Integer id, HttpSession session);

    Result operating(Integer id,Integer cid);

    Result updateCustomer(Customer customer,HttpSession session);

    List<Cuspay> getCustomerInfo(Integer id);

    List<Vpackage> getPackageInfo(Integer id);

    LayuiTableResult getCustomerattendanceList(Integer id ,PageBean pageBean);

    Result addCuspay(Customer customer,HttpSession session);

    List<Vpackage> getPackageList();

    Result addCuspayList(CuspayArry[] array);

    List<ProTypes> getProduceList();

    Result addProduce(CuspayArry[] proTypes);

    Result sign(Integer uid,Integer cid, Integer isin, String whyNo);

    LayuiTableResult selectCheckingByid(Integer id);

    Result saveSignIn(Customerattendance customerattendance);
}
