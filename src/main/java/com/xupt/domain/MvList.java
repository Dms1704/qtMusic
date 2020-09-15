package com.xupt.domain;

public class MvList {
    private Integer sid;//mv对应的歌曲的id
    private Integer mv_num;//mv播放量
    private String mv_url;//mv播放的地址
    private String author;
    private String songname;
    private String img;//mv的封面图片

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMv_num() {
        return mv_num;
    }

    public void setMv_num(Integer mv_num) {
        this.mv_num = mv_num;
    }

    public String getMv_url() {
        return mv_url;
    }

    public void setMv_url(String mv_url) {
        this.mv_url = mv_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
