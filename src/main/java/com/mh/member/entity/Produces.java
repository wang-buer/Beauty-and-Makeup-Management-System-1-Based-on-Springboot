package com.mh.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 产品表
 * @Author MH
 * @Date 2020/2/2 11:49
 */
@Data
@TableName(value = "produce")
public class Produces {
    public Produces() {
    }

    public Produces(Integer num) {
        this.num = num;
    }

    private String name;
    private Integer id;
    private Integer type;
    private double price;
    private Integer num;

}
