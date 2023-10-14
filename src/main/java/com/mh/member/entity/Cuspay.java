package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

/**
 * 顾客消费记录表
 *
 * @Author MH
 * @Date 2020/2/1 20:31
 */
@Data
public class Cuspay {
    @TableId
    private Integer id;
    private Double price; //消费价格
    private Integer cusid; //顾客id
    private Integer produceId; //商品id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paydate; //支付日期
    private Integer empid; //员工id
    private Integer vipid; //会员卡类型id
    private Integer pack; //套餐编号
    private String paydesc; //描述信息

    private String empname;//办卡员工
    private String vname; //卡类型
    private Double groupPrice; //价格

}
