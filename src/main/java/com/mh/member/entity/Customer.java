package com.mh.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author MH
 * @Date 2020/1/16 19:27
 * 顾客
 */
@Data
public class Customer {
    private Integer cid;
    private String cname;//会员姓名
    private Integer age;
    private String address;
    private String phone;
    private String birthday;
    private String bodyfat;//脂肪率
    private Integer fatmass; //脂肪量
    private Double weight; //体重
    private Double stature; //身高
    private String bust; //胸围
    private String hip; //臀围
    private String waistline; //腰围
    private Integer faceCount; //面部护理剩余次数
    private Integer localdecrementCount; //局减剩余次数
    private Integer massageCount; //剩余按摩次数
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    private Double price; //消费价格
    private Integer vipid; //会员卡类型id
    private String paydesc; //描述信息
    //关联消费记录表
    private Cuspay cuspay;
}
