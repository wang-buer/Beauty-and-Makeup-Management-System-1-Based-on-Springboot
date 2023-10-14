package com.mh.member.controller;

import com.mh.member.entity.EC;
import com.mh.member.entity.Echarts;
import com.mh.member.service.ConsumerService;
import org.apache.shiro.crypto.hash.Hash;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author MH
 * @Date 2020/2/8 16:12
 * 顾客活跃度
 */
@Controller
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @RequestMapping("livenessPage")
    public String livenessPage() {
        //查看一个月内顾客的到店次数
        /*
        * y：到店次数
        * x: 日期 1-30 每次加 5
        * 折线：顾客
        * */
     return "/member/livenessPage";
    }

    @RequestMapping("getJSONData")
    @ResponseBody
    public Map<String, Object> getJSONData(Integer month,String type) {

        if (type != null && type!="") EC.TYPE=type; //设置类型

        LocalDate now = LocalDate.now();
        if (month == null) {                        //设置月
            month = now.getMonthValue();
        }
        List<EC> ecs = consumerService.getEchartsData(month);
        if (ecs==null || ecs.size()<=0){
            Map map = new HashMap();
            map.put("info",month+" 月还没有数据");
            return map;
        }
        Map<String, Object> echarts = echarts(month, type, ecs);
        return echarts;
    }

    public Map<String, Object> echarts(Integer month,String type,List<EC> ecs) {


        Map<String, Object> map = new HashMap<>();
        Map<String, Object> m = new HashMap<>();
        m.put("text", EC.TEXT);
        map.put("title", m);
        Map<String, String > m1 = new HashMap<>(); //设置鼠标移入显示竖线
        m1.put("trigger", "axis");
        Map<String, String > mm = new HashMap<>();
        mm.put("type","line");
        m.put("axisPointer",mm);
        map.put("tooltip", m1);
        Map<String, Object> m2 = new HashMap<>();
        List<String> collect = ecs.stream().map(EC::getName).collect(Collectors.toList()); //获取name的集合
        m2.put("data", collect);
        map.put("legend", m2);
        Map<String, Object> m3 = new HashMap<>();
        m3.put("left", "3%");
        m3.put("right", "4%");
        m3.put("bottom", "3%");
        m3.put("containLabel",true);
        map.put("grid", m3);
        Map<String, Object> m4 = new HashMap<>();
        m4.put("type", "category");
        m4.put("boundaryGap", "false");
        m4.put("data", ecs.get(0).getDays());
        map.put("xAxis", m4);
        Map<String, Object> m5 = new HashMap<>();
        m5.put("type", "value");
        Map<String, Object> mm5 = new HashMap<>();
        mm5.put("show", false); //去掉刻度
        m5.put("axisTick",mm5);
        m5.put("min", 0);
        m5.put("max",5);
        Map<String, Object> mm6 = new HashMap<>();
        mm6.put("formatter",new Integer[]{0,1,2,3,4,5});
        m5.put("axisLabel",mm6);
        map.put("yAxis", m5);
        map.put("series", ecs);
        ecs.forEach(System.out::println);
        return map;
    }
}
