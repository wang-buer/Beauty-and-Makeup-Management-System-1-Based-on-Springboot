package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 套餐实体
 *
 * @Author MH
 * @Date 2020/2/1 20:22
 */
@TableName("package")
@Data
public class Vpackage {
    @TableId
    private Integer pid;
    private String pname;
    private Double price;
    private Integer vipid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private int istrue;//是否有效 1为有效 0无效
    private List<Produces> produces; //对应产品表
}
