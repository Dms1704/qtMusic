package com.xupt.domain;

import java.io.Serializable;

public class Category implements Serializable {

    private Integer cid;
    private Integer sid;
    private String cname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", sid=" + sid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
