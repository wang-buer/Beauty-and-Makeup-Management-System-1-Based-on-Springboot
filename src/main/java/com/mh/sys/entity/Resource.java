package com.mh.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单表
 */
@Data
public class Resource implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    private Integer pId; //父级菜单id
    private String cname; // 菜单名称
    private  String perms; //权限，如：user:list,user:create
    private  String icon; //菜单图标
    private  String url; //菜单路径
    private List<Resource> children;
    private  Boolean isStop; //是否停用
    private  Integer sn; // 排序
    private  String remark; //备注
    private  String opId; //创建者id
    private Date create_time; //创建时间
    private  Date update_time; //修改时间
    private Boolean isAdmin; //是否是管理员
    private  String target;
    private int isdel; //标识
}
