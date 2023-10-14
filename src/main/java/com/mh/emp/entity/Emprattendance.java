package com.mh.emp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author MH
 * @Date 2020/1/13 20:17
 * 签到
 */
@Data
public class Emprattendance {
    @TableId
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime indate;
    private Integer empid;
    private Boolean isin; //是否签到
    private String whyNo; //没签到的原因
    @TableField(exist = false)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    @TableField(exist = false)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;
}
