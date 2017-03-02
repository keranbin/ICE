package com.cn.keranbing.ice.Glide.common;

import android.util.Log;

import com.cn.keranbing.ice.Glide.bean.Gif;
import com.cn.keranbing.ice.Common.Utils.LogUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by keranbin on 2017/2/22.
 */

public class JsoupUtils {
    public final static String TAG="JsoupUtils";
    /**
     * 抓取网页动态图
     * @param urls
     * @param type
     * @return
     */
    public static ArrayList<Gif> getGif(String urls,int type){
        ArrayList<Gif> lists = new ArrayList<>();
        try {
            Document document=null;
            document= Jsoup.parse(new URL(urls),5000);
            LogUtil.i(TAG,document.toString());
            Elements es_item = document.getElementsByClass("item");
            for (int i = 0; i < es_item.size(); i++) {
                Element et = es_item.get(i).getElementsByTag("h3").first();
                if (et != null) {
                    String title = et.getElementsByTag("b").text();
                    String url = es_item.get(i).select("img").first().attr("src");
                    Log.i("jsoup", title + "\t\t" + url + "\n");
                    lists.add(new Gif(title, url));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }
}
