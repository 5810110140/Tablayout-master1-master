package com.codingdemos.tablayout;

public class harvast_value {
    private String id_harvast;
    private String type;
    private String value;
    private String weight;
    private String date;

    public harvast_value(String id_harvast, String type, String value, String weight, String date) {
        this.id_harvast = id_harvast;
        this.type = type;
        this.value = value;
        this.date = date;
        this.weight = weight;
    }
    public harvast_value(){

    }

    public String getId_harvast() {
        return id_harvast;
    }

    public void setId_harvast(String id_harvast) {
        this.id_harvast = id_harvast;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
