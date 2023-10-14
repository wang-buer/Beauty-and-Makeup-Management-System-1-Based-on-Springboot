package com.mh.member.service;

import com.mh.member.entity.EC;
import com.mh.member.entity.Echarts;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author MH
 * @Date 2020/2/8 17:28
 */
public interface ConsumerService {
    List<EC> getEchartsData(int month);
}
