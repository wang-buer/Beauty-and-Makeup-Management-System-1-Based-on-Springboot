package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 会员类型表
 *
 * @Author MH
 * @Date 2020/2/1 11:17
 */
@Data
public class Vip {
    @TableId
    private Integer id;
    private String vname; //会员类型
    private Double vprice; //价格
    @TableField(exist = false)
    private String describe; //描述
    @TableField(exist = false)
    private Vpackage vpackage;//套餐实体
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createTime")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "modifyDate")
    private LocalDateTime modifyDate;
    private Integer vtid; //套餐id
}
