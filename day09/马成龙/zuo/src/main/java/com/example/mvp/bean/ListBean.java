package com.example.mvp.bean;

public class ListBean {
    private int img;

    public ListBean() {
    }

    public ListBean(int img) {
        this.img = img;
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
