package com.xupt.domain;

import java.io.Serializable;

public class Song implements Serializable {

    private Integer sid;
    private String songname;
    private String url;
    private String img;
    private boolean mv_status;
    private String author;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isMv_status() {
        return mv_status;
    }

    public void setMv_status(boolean mv_status) {
        this.mv_status = mv_status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Song{" +
                "sid=" + sid +
                ", songname='" + songname + '\'' +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", mv_status=" + mv_status +
                ", author='" + author + '\'' +
                '}';
    }
}
