package com.xupt.domain;

import java.io.Serializable;
import java.util.Date;


public class Discuss implements Serializable {
    private Integer did;
    private Date Dtime;
    private String describe;
    private Integer sid;
    private Integer uid;


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Date getDtime() {
        return Dtime;
    }

    public void setDtime(Date dtime) {
        Dtime = dtime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "did=" + did +
                ", Dtime=" + Dtime +
                ", describe='" + describe + '\'' +
                ", sid=" + sid +
                ", uid=" + uid +
                '}';
    }
}
