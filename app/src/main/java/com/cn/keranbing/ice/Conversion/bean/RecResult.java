package com.cn.keranbing.ice.Conversion.bean;

import java.io.Serializable;

/**
 * Created by keranbin on 2017/4/6.
 */

public class RecResult implements Serializable{
    private String strResultTitle;
    private String strResultContent;

    public RecResult(String strResultTitle,String strResultContent){
        this.strResultTitle=strResultTitle;
        this.strResultContent=strResultContent;
    }

    public String getStrResultTitle() {
        return strResultTitle;
    }

    public void setStrResultTitle(String strResultTitle) {
        this.strResultTitle = strResultTitle;
    }

    public String getStrResultContent() {
        return strResultContent;
    }

    public void setStrResultContent(String strResultContent) {
        this.strResultContent = strResultContent;
    }

    @Override
    public String toString() {
        return "RecResult{" +
                "strResultTitle='" + strResultTitle + '\'' +
                ", strResultContent='" + strResultContent + '\'' +
                '}';
    }
}
