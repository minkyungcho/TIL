package com.example.p577;

import java.io.Serializable;

public class Memo implements Serializable {
    String name;
    String date;
    String title;
    String cont;

    public Memo() {
    }

    public Memo(String name, String date, String title, String cont) {
        this.name = name;
        this.date = date;
        this.title = title;
        this.cont = cont;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

}
