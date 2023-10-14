package com.mh.product.entity;

public class Produce {
    private String name;
    private Integer id;
    private Integer type;
    private double price;
    private String typename;
    private Integer num;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Produce{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", typename='" + typename + '\'' +
                ", num=" + num +
                ", desc='" + desc + '\'' +
                '}';
    }
}
