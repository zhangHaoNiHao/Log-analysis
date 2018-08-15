package com.entity;

import java.io.Serializable;

public class data implements Serializable{

    private String city;
    private String date;
    private String day;
    private Integer traffic;
    private String type;
    private String id;

    public data() {    }

    public data(String city, String date, String day, Integer traffic, String type, String id) {
        this.city = city;
        this.date = date;
        this.day = day;
        this.traffic = traffic;
        this.type = type;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
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

    @Override
    public String toString() {
        return "data{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", traffic=" + traffic +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
