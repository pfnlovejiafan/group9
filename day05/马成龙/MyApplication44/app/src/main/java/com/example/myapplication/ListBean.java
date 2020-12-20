package com.example.myapplication;

public class ListBean {
    private  int img;

    public ListBean(int img) {
        this.img = img;
    }

    public ListBean() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "img=" + img +
                '}';
    }
}
