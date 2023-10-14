package com.mh.vip.service.Impl;

import com.mh.product.entity.CusPayL;
import com.mh.vip.entity.Vip_ljl;
import com.mh.vip.service.Vservice;
import com.mh.vip.mapper.VipMappers_ljl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VserviceImpl implements Vservice {
    @Autowired
    VipMappers_ljl v;
    @Override
    public List<Vip_ljl> getVipList() {
        return v.getVipList();
    }

    @Override
    public int addvip(Vip_ljl vl) {
        return v.addvip(vl);
    }

    @Override
    public int mfyvip(Vip_ljl vs) {
        return v.mfyvip(vs);
    }

    @Override
    public Vip_ljl getonevip(Integer vid) {
        return v.getonevip(vid);
    }

    @Override
    public int delvip(Integer vid) {
        return v.delvip(vid);
    }

    @Override
    public List<CusPayL> vipReport(String year, String month, String prevMonth, Integer empid) {
        return v.vipReport(year,month ,prevMonth,empid);
    }

}
