package com.mh.member.service.impl;

import com.mh.member.entity.Customerattendance;
import com.mh.member.entity.EC;
import com.mh.member.entity.Echarts;
import com.mh.member.mapper.CustomerattendanceMapper;
import com.mh.member.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author MH
 * @Date 2020/2/8 17:28
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    public CustomerattendanceMapper customerattendanceMapper;

    @Override
    public List getEchartsData(int month) {
        List<EC> list= new ArrayList<>();
        try {
            List<Echarts> echartsData = customerattendanceMapper.getEchartsData(month);
            for (Echarts e : echartsData) {
                EC ec = new EC(e.getName(), Arrays.asList(e.getA(),e.getB(),e.getC(),e.getD(),e.getE(),e.getF()));
                list.add(ec);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
