package com.dorofeyev.robotscrud;

/**
 * Created by xor on 7/21/15.
 */
public class Robot {

    private int id;
    private String name;


    Robot(){}

    Robot (int id, String name) {
        this.id = id;
        this.name = name;
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

}
