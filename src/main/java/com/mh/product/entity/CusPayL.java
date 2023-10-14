package com.mh.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CusPayL {
    @TableId
    private Integer id;
    private double price;
   private String cname;
    private  String  pname;
   private String  pacname;
private String vipname;

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public String getPacname() {
        return pacname;
    }

    public void setPacname(String pacname) {
        this.pacname = pacname;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paydate;
    private String empname;
    private  String vname;
    private String paydesc;
    private Integer meal_sum;
    private Integer produce_sum;
    private String proname;

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    @Override
    public String toString() {
        return "CusPay{" +
                "id=" + id +
                ", price=" + price +
                ", cname='" + cname + '\'' +
                ", pname='" + pname + '\'' +
                ", paydate=" + paydate +
                ", empname='" + empname + '\'' +
                ", vname='" + vname + '\'' +
                ", paydesc='" + paydesc + '\'' +
                ", meal_sum=" + meal_sum +
                ", produce_sum=" + produce_sum +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public LocalDateTime getPaydate() {
        return paydate;
    }

    public void setPaydate(LocalDateTime paydate) {
        this.paydate = paydate;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getPaydesc() {
        return paydesc;
    }

    public void setPaydesc(String paydesc) {
        this.paydesc = paydesc;
    }

    public Integer getMeal_sum() {
        return meal_sum;
    }

    public void setMeal_sum(Integer meal_sum) {
        this.meal_sum = meal_sum;
    }

    public Integer getProduce_sum() {
        return produce_sum;
    }

    public void setProduce_sum(Integer produce_sum) {
        this.produce_sum = produce_sum;
    }
}
