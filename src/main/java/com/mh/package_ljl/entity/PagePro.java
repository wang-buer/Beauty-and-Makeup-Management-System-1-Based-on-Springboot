package com.mh.package_ljl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "pagepro")
public class PagePro {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer packageid;
    @TableField(value = "produceid")
    private Integer roduceid;
    private Integer num;
    @TableField(exist = false)
    private String pacname;
    @TableField(exist = false)
    private String proname;

    @Override
    public String toString() {
        return "PagePro{" +
                "id=" + id +
                ", packageid=" + packageid +
                ", roduceid=" + roduceid +
                ", num=" + num +
                ", pacname='" + pacname + '\'' +
                ", proname='" + proname + '\'' +
                '}';
    }

    public String getPacname() {
        return pacname;
    }

    public void setPacname(String pacname) {
        this.pacname = pacname;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackageid() {
        return packageid;
    }

    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    public Integer getRoduceid() {
        return roduceid;
    }

    public void setRoduceid(Integer roduceid) {
        this.roduceid = roduceid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


}
