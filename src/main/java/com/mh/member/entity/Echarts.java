package com.mh.member.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/2/9 11:53
 * 折线图对应实体
 */
@Data
public class Echarts {
    public Echarts() {
    }

    public Echarts(String name, List<Integer> data) {
        this.name = name;
        this.data = data;
    }

    private String name;
    private int a; // 1-5
    private int b; // 6-10
    private int c; // 11-15
    private int d; // 16-20
    private int e; // 21-25
    private int f; // 26-30

    private String type="line";
    private String stack="总量";
    private List<Integer> data;
    private List<String> days= Arrays.asList("1-5","6-10","11-15","16-20","21-25","26-30");
}
