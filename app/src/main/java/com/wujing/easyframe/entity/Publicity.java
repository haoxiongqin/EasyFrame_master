package com.wujing.easyframe.entity;

import java.io.Serializable;


public class Publicity implements Serializable {
    String title;
    public Publicity() {

    }
    public Publicity(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
