package com.example.day5_jiaying1.Info;

public class Four_child {
    private int childImgid;
    private String childtitles;
    private String childdesc;

    public Four_child(int childImgid, String childtitles, String childdesc) {
        this.childImgid = childImgid;
        this.childtitles = childtitles;
        this.childdesc = childdesc;
    }

    public int getChildImgid() {
        return childImgid;
    }

    public void setChildImgid(int childImgid) {
        this.childImgid = childImgid;
    }

    public String getChildtitles() {
        return childtitles;
    }

    public void setChildtitles(String childtitles) {
        this.childtitles = childtitles;
    }

    public String getChilddesc() {
        return childdesc;
    }

    public void setChilddesc(String childdesc) {
        this.childdesc = childdesc;
    }
}
