package com.example.xiaoshixun.base;

public class JsonBean {
    private Integer image;
    private String title;

    public JsonBean() {
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonBean(Integer image, String title) {
        this.image = image;
        this.title = title;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "image=" + image +
                ", title='" + title + '\'' +
                '}';
    }
}
