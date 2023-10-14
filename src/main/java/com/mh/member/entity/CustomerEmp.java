package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 顾客和员工的关联表
 *
 * @Author MH
 * @Date 2020/2/3 15:26
 */
@Data
public class CustomerEmp {
    @TableId
    private Integer id;
    private Integer empid;
    private Integer cusid;
}
