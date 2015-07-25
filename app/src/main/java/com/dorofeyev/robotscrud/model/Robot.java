package com.dorofeyev.robotscrud.model;

/**
 * Created by xor on 7/21/15.
 * Класс описывающий робота
 */
public class Robot {

    private int id;
    private String name;
    private String type;
    private int year;

    private static final int VALUE_NOT_SET = -1;

    public Robot(){}

    public Robot(int id, String name, String type, int year) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public Robot (int id, String name) {
        this(id, name, "", VALUE_NOT_SET);
    }

    public Robot (String name, String type, int year) {
        this(VALUE_NOT_SET, name, type, year);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
