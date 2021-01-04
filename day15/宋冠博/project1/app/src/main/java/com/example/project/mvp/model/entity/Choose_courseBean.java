package com.example.project.mvp.model.entity;

import java.util.List;

public class Choose_courseBean {
    private String title;
    private List<String> course;

    public Choose_courseBean() {
    }

    public Choose_courseBean(String title, List<String> course) {
        this.title = title;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Choose_courseBean{" +
                "title='" + title + '\'' +
                ", course=" + course +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }
}
