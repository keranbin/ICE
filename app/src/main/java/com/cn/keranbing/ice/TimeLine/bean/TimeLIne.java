package com.cn.keranbing.ice.TimeLine.bean;

/**
 * Created by keranbin on 2017/3/22.
 */

public class TimeLine {
    private String title;
    private int type;
    private String detail;

    public TimeLine(int type,String title,String detail){
        this.type=type;
        this.title=title;
        this.detail=detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", detail='" + detail + '\'' +
                '}';
    }
}
