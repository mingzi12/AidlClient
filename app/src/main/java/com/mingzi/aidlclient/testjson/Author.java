package com.mingzi.aidlclient.testjson;

/**
 * Created by Administrator on 2016/3/11.
 */
public class Author {

    private int id;
    private String name;

    public Author() {
    }

    public Author(int id , String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
