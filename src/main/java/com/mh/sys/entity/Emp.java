package com.mh.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

/**
 * 员工表
 */
@Data
public class Emp {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String empname; //用户名
    private String emptype; // 用户类型
    private String login; // 登录名
    private String pwd; //密码
    private  String name; //姓名
    private Integer isdel; //是否删除,0:存在；1:删除

    @TableField(exist = false)
    private String roles; //用户角色

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}

