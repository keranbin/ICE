package com.cn.keranbing.ice.Conversion.bean;

import java.io.Serializable;

/**
 * Created by keranbin on 2017/3/30.
 */

public class Home implements Serializable{
    private int icon;
    private String title;

    public Home(int icon,String title){
        this.icon=icon;
        this.title=title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Home{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                '}';
    }
}
