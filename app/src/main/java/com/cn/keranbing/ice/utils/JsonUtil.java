package com.cn.keranbing.ice.utils;

import com.example.keranbin.business.help.treeHelp.Node;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by keranbin on 2016/9/2.
 */
public class JsonUtil {
    /**
     * 集合转化成Json形式的字符串
     * @param list
     * @return
     */
    public static String ListToJson(List<Node> list) {
        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;
    }

}
