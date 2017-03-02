package com.cn.keranbing.ice.Common.Utils;

/**
 * Created by keranbin on 2016/8/10.
 */
public class StrTestUtil {
    /*
    * 判断字符串是否为null
    * */
    public static boolean isEmpty(String string) {
        if (string.equals(""))
            return true;
        return false;
    }

    /*
   * 判断字符串是否为null
   * */
    public static boolean isNull(String string) {
        if (string == null)
            return true;
        return false;
    }

     /*
    * 判断字符串是否为null或者为""
    * */
    public  static boolean isEmptyOrNull(String string){
        return (isEmpty(string)&&isNull(string));
    }

}
