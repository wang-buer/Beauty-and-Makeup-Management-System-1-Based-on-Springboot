package com.mh.store.service.impl;

import com.mh.product.entity.CusPayL;
import com.mh.store.mapper.StoreMapper;
import com.mh.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImp implements StoreService {
    @Autowired
    StoreMapper sm;
    @Override
    public CusPayL getInToday() {
        return sm.getInToday();
    }

    @Override
    public CusPayL getInThisWeek() {
        return sm.getInThisWeek();
    }

    @Override
    public CusPayL getInprevWeek() {
        return sm.getInprevWeek();
    }

    @Override
    public CusPayL getInThisMonth() {
        return sm.getInThisMonth();
    }

    @Override
    public CusPayL getInPrevMonth() {
        return sm.getInPrevMonth();
    }

    @Override
    public CusPayL getInThisYear() {
        return sm.getInThisYear();
    }

    @Override
    public CusPayL getInPrevYear() {
        return sm.getInPrevYear();
    }

    @Override
    public CusPayL getThisSeason() {
        return sm.getThisSeason();
    }

    @Override
    public CusPayL getPrevSeason() {
        return sm.getPrevSeason();
    }
}
