package com.mh.store.service;

import com.mh.product.entity.CusPayL;

public interface StoreService {
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
