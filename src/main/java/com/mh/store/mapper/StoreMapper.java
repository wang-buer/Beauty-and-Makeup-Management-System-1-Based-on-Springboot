package com.mh.store.mapper;

import com.mh.product.entity.CusPayL;

import java.util.List;

public interface StoreMapper {
    CusPayL getInToday();
    CusPayL getInThisWeek();
    CusPayL getInprevWeek();
    CusPayL getInThisMonth();
    CusPayL getInPrevMonth();
    CusPayL getInThisYear();
    CusPayL getInPrevYear();
    CusPayL getThisSeason();
    CusPayL getPrevSeason();
}
