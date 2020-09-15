package com.xupt.domain;

public class SongList {
    private Integer play_num;
    private Integer sid;
    private Integer mv_status;
    private String songname;
    private String author;

    public Integer getPlay_num() {
        return play_num;
    }

    public void setPlay_num(Integer play_num) {
        this.play_num = play_num;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMv_status() {
        return mv_status;
    }

    public void setMv_status(Integer mv_status) {
        this.mv_status = mv_status;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
