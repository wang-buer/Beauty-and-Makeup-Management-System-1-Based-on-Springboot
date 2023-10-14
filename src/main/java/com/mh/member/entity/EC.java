package com.mh.member.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/2/9 14:55
 */
@Data
public class EC {
    public EC() {
    }

    public EC(String name, List<Integer> data) {
        this.name = name;
        this.data = data;
    }

    private String name;
    /*line（折线图）、bar（柱状图）、pie（饼图）、scatter（散点图）、graph（关系图）、tree（树图）*/
    public static String TYPE="line"; //系列类型
    public static String TEXT="顾客活跃度统计图";
    private  String type=TYPE;
    private List<Integer> data;
    private List<String> days= Arrays.asList("1-5","6-10","11-15","16-20","21-25","26-30");

}
