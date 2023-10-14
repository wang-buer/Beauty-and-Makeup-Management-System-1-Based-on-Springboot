package com.mh.vip.entity;


import java.util.Date;

public class Vip_ljl {

           private Integer  id;
           private String  vname;
           private double vprice;
           private String describe;
           private Date createTime;
            private Date modifyDate;
            private Integer vtid;
            private String pacname;


    public String getPacname() {
        return pacname;
    }

    public void setPacname(String pacname) {
        this.pacname = pacname;
    }

    public Integer getVtid() {
        return vtid;
    }

    public void setVtid(Integer vtid) {
        this.vtid = vtid;
    }

    @Override
    public String toString() {
        return "Vip_ljl{" +
                "id=" + id +
                ", vname='" + vname + '\'' +
                ", vprice=" + vprice +
                ", describe='" + describe + '\'' +
                ", createTime=" + createTime +
                ", modifyDate=" + modifyDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public double getVprice() {
        return vprice;
    }

    public void setVprice(double vprice) {
        this.vprice = vprice;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

