package com.cn.keranbing.ice.TimeLine.bean;

/**
 * Created by keranbin on 2017/3/29.
 */

public class Personal {
    private String strTitle;
    private String strSubTitle;

    public Personal(String strTitle,String strSubTitle){
        this.strTitle=strTitle;
        this.strSubTitle=strSubTitle;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrSubTitle() {
        return strSubTitle;
    }

    public void setStrSubTitle(String strSubTitle) {
        this.strSubTitle = strSubTitle;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "strTitle='" + strTitle + '\'' +
                ", strSubTitle='" + strSubTitle + '\'' +
                '}';
    }
}
