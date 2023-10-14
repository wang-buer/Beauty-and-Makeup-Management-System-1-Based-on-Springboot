package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Author MH
 * @Date 2020/2/7 16:18
 * 产品类型
 */
@Data
@TableName("protype")
public class ProTypes {
    @TableId
    private Integer id;
    private String name; //类型名称
    private String desc; //描述

    private List<Produces> produces; //产品
}
