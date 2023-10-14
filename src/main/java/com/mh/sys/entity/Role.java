package com.mh.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @Author MH
 * @Date 2020/1/10 16:15
 * 角色
 */
@Data
public class Role {
        @TableId
        private Integer rid;
        private String rname;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime createDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime updateDate;
        private String des;
        private Integer isdel;
}


