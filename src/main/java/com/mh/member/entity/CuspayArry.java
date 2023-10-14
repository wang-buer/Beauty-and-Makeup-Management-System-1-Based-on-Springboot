package com.mh.member.entity;

import com.mh.product.entity.Produce;
import lombok.Data;

import java.util.List;

/**
 * @Author MH
 * @Date 2020/2/6 22:31
 */
@Data
public class CuspayArry {
    public CuspayArry() {
    }

    public CuspayArry(Integer pack, int sum, String name, Double groupPrice, Double price) {
        this.pack = pack;
        this.sum = sum;
        this.name = name;
        this.groupPrice = groupPrice;
        this.price = price;
    }
    private Integer empId;//销售员工id
    private Integer cid;//顾客id
    private Integer pack; //套餐id
    private int sum; //套餐/产品 数量
    private String name; //套餐名称
    private Double groupPrice; //套餐总价格
    private Double price; //套餐单价
    private Integer produceId; //产品id
    private Double prices; //商品价格
    private Produces[] produces;//产品
}
