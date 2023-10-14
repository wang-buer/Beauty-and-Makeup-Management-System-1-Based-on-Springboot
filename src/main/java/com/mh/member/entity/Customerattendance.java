package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 顾客考勤信息
 *
 * @Author MH
 * @Date 2020/2/4 21:24
 */
@Data
public class Customerattendance {
    public Customerattendance() {
    }

    public Customerattendance(Integer cusid, LocalDateTime indate, int isin, String bodyfat, Integer fatmass, Double weight, Double stature, String bust, String hip, String waistline, Integer empid, String whyNo) {
        this.cusid = cusid;
        this.indate = indate;
        this.isin = isin;
        this.bodyfat = bodyfat;
        this.fatmass = fatmass;
        this.weight = weight;
        this.stature = stature;
        this.bust = bust;
        this.hip = hip;
        this.waistline = waistline;
        this.empid = empid;
        this.whyNo = whyNo;
    }

    public Customerattendance(Integer id, Integer cusid, LocalDateTime indate, int isin, String bodyfat, Integer fatmass, Double weight, Double stature, String bust, String hip, String waistline, Integer empid, String whyNo) {
        this.id = id;
        this.cusid = cusid;
        this.indate = indate;
        this.isin = isin;
        this.bodyfat = bodyfat;
        this.fatmass = fatmass;
        this.weight = weight;
        this.stature = stature;
        this.bust = bust;
        this.hip = hip;
        this.waistline = waistline;
        this.empid = empid;
        this.whyNo = whyNo;
    }

    @TableId
    private Integer id;
    private Integer cusid;
    private String cname;
    private LocalDateTime indate;
    private int isin;
    private String bodyfat;
    private Integer fatmass;
    private Double weight;
    private Double stature;
    private String bust;
    private String hip;
    private String waistline;
    private String empname;
    private Integer empid;
    private String whyNo;
}
