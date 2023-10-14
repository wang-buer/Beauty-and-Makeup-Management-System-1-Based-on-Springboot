package com.mh.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class IntoGoods {
   private Integer id;
    private Integer empid;
    private Integer num;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime intotime;
    private Integer pid;
    private  String empname;//员工
    private String pname;//商品
    private double totalprice;

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public LocalDateTime getIntotime() {
        return intotime;
    }

    public void setIntotime(LocalDateTime intotime) {
        this.intotime = intotime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "IntoGoods{" +
                "id=" + id +
                ", empid=" + empid +
                ", num=" + num +
                ", intotime=" + intotime +
                ", pid=" + pid +
                ", emoname='" + empname + '\'' +
                ", pname='" + pname + '\'' +
                '}';
    }
}
