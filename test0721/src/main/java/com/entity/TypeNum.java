package com.entity;

public class TypeNum {

    private String type;
    private String id;
    private Integer sum;

    public TypeNum() { }

    public TypeNum(String type, String id, Integer num) {
        this.type = type;
        this.id = id;
        this.sum = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer num) {
        this.sum = num;
    }

    @Override
    public String toString() {
        return "TypeNum{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", num='" + sum + '\'' +
                '}';
    }
}
