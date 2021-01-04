package com.example.project.mvp.model.entity;

public class FragmentHomeRvBean {
    private Integer image1;
    private String title;
    private String ke;
    private String pice;
    private Integer iamge2;
    private String xue;
    private String gou;

    public FragmentHomeRvBean(Integer image1, String title, String ke, String pice, Integer iamge2, String xue, String gou) {
        this.image1 = image1;
        this.title = title;
        this.ke = ke;
        this.pice = pice;
        this.iamge2 = iamge2;
        this.xue = xue;
        this.gou = gou;
    }

    public Integer getImage1() {
        return image1;
    }

    public void setImage1(Integer image1) {
        this.image1 = image1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKe() {
        return ke;
    }

    public void setKe(String ke) {
        this.ke = ke;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public Integer getIamge2() {
        return iamge2;
    }

    public void setIamge2(Integer iamge2) {
        this.iamge2 = iamge2;
    }

    public String getXue() {
        return xue;
    }

    public void setXue(String xue) {
        this.xue = xue;
    }

    public String getGou() {
        return gou;
    }

    public void setGou(String gou) {
        this.gou = gou;
    }
}
