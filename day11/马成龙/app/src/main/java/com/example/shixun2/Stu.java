package com.example.shixun2;

public class Stu {
    private String title;
    private Integer img;

    public Stu(String title, Integer img) {
        this.title = title;
        this.img = img;
    }

    public Stu() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    @Override
    public String
    toString() {
        return "Stu{" +
                "title='" + title + '\'' +
                ", img=" + img +
                '}';
    }
}
