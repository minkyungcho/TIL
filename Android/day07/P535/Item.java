package com.example.p535;

public class Item {
    String name;
    String phone;
    String area;
    String img;

    public Item() {
    }

    public Item(String name, String phone, String area, String img) {
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.img = img;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
