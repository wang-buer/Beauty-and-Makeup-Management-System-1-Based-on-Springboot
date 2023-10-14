package com.mh.vip.service;

import com.mh.product.entity.CusPayL;
import com.mh.vip.entity.Vip_ljl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Vservice {
    List<Vip_ljl> getVipList();
    int addvip(Vip_ljl vl);
    int mfyvip(Vip_ljl v);
    Vip_ljl getonevip(@Param("id")Integer vid);
    int delvip(@Param("vid")Integer vid);
    List<CusPayL> vipReport(@Param("year") String year,
                            @Param("month")String month,
                            @Param("prevMonth")String prevMonth,
                            @Param("empid")Integer empid);//个人会员销售记录
}
