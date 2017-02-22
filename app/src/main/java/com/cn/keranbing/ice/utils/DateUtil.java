package com.cn.keranbing.ice.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by keranbin on 2016/8/1.
 */
public class DateUtil {
    /*
    * 获取当前时间时间戳
    * */
    public static Long getTimeStamp() {
        //方法 一  :建议，最快
        return System.currentTimeMillis();
        //方法 二  :最慢，不建议使用
        //  return Calendar.getInstance().getTimeInMillis();
        //方法 三
        //  return  new Date().getTime();
    }

    /*
    * 获取int类型当前年份
    * */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static String getStrYear(){
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    /*
    * 获取int类型当前月份
    * */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static String getStrMonth(){
        return new SimpleDateFormat("MM").format(new Date());
    }

    /*
    * 获取当月的第几天，从1开始
    * */
    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /*
    * 获取当年的第几天，从1开始
    * */
    public static int getDayOfYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /*
   * 获取这周的第几天，返回周几
   * */
    public static String getDayOfWeek() {
        return returnWeekStr(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    }

    /*
    * 判断传入的时间戳是否是当年
    * */
    public static boolean isThisYear(String strNum) {
        if (strNum != null && !strNum.equals("")) {
            String strYear = new SimpleDateFormat("yyyy").format(new Date(Long.parseLong(strNum)));
            if (strYear.equals(String.valueOf(getYear()))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
   * 判断传入的时间戳是否是当前月份
   * */
    public static boolean isThisMonth(String strNum) {
        if (strNum != null && !strNum.equals("")) {
            String strYear = new SimpleDateFormat("yyyy").format(new Date(Long.parseLong(strNum)));
            if (strYear.equals(String.valueOf(getMonth()))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
  * 判断传入的时间戳是否是当前月份的今天
  * */
    public static boolean isThisDay(String strNum) {
        if (strNum != null && !strNum.equals("")) {
            String strYear = new SimpleDateFormat("yyyy").format(new Date(Long.parseLong(strNum)));
            if (strYear.equals(String.valueOf(getDayOfMonth()))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
    * 按照输入的格式转化传入的时间戳
    * */
    public static String getDateTimeByTimeFormat(String num, String timeFormat) {
        if (!num.equals("null") && !num.equals(""))
            return new SimpleDateFormat(timeFormat).format(new Date(Long.parseLong(num)));
        return "";
    }

    /*
  * 按照返回输入的格式时间
  * */
    public static String getNowDateTimeByTimeFormat(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(new Date());
    }

    /*
    * 判断传入的两个时间相差几天
    * */
    public static String getDifferDays(Date date1, Date date2) {
        return String.valueOf((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
    }

    /*
    * 判断传入的时间戳是周几
    * */
    public static String getWeekStr(String strNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(Long.parseLong(strNum)));
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return returnWeekStr(intWeek);
    }

    /*
    * 根据传入的数据返回周几
    * */
    public static String returnWeekStr(int intWeek) {
        String strWeek = "";
        switch (intWeek) {
            case 1:
                strWeek = "星期日";
                break;
            case 2:
                strWeek = "星期一";
                break;
            case 3:
                strWeek = "星期二";
                break;
            case 4:
                strWeek = "星期三";
                break;
            case 5:
                strWeek = "星期四";
                break;
            case 6:
                strWeek = "星期五";
                break;
            case 7:
                strWeek = "星期六";
                break;
        }
        return strWeek;
    }


    /**
     * 时间转换,如果传入的时间为年份为当年，则返回MM/dd格式的时间，否则返回yyyy/MM/dd格式的时间
     *
     * @param Date
     * @return
     */
    public static String DateChange(String Date) {
        SimpleDateFormat simpleDateFormat;
        String strDate = null;
        String nowYear = getYear() + "";
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            date = simpleDateFormat.parse(Date + " 00:00:00");
            if (nowYear.equals(Date.substring(0, 4))) {
                simpleDateFormat = new SimpleDateFormat("MM/dd");
                strDate = simpleDateFormat.format(date);
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                strDate = simpleDateFormat.format(date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return strDate;
    }


    /**
     * 时间转换,如果传入的时间为年份为当年，则返回MM/dd格式的时间，否则返回yyyy/MM/dd格式的时间
     *
     * @param
     * @return
     */
    public static String DateChange1(String arr, String time13) throws ParseException {
        String arriveDate = arr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        Date currentTime = new Date(System.currentTimeMillis());
        String sysTime = sdf.format(currentTime);
        sysTime = sysTime.replace("@", "").replace("-", "").substring(0, 8);
        arriveDate = arriveDate.replace("-", "");
        int sysYearTimeNum = Integer.parseInt(sysTime.replace("@", "")
                .replace("-", "").substring(0, 4));// 系统当前时间转为int数值
        int arriveYearDateNum = Integer.parseInt(arriveDate.replace("-", "")
                .substring(0, 4));// 出差单获取到达时间转为int数值
        sdf = new SimpleDateFormat("E");
        String week = sdf.format(currentTime);// 获取今天星期几
        int sysMonth = Integer.parseInt(sysTime.substring(4, 6));
        int arrMonth = Integer.parseInt(arriveDate.substring(4, 6));
        int arrDay = Integer.parseInt(arriveDate.substring(6, 8));
        int sysDay = Integer.parseInt(sysTime.substring(6, 8));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, arriveYearDateNum);
        calendar.set(Calendar.MONTH, arrMonth - 1);
        int arrDays = calendar.getActualMaximum(Calendar.DATE);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, sysYearTimeNum);
        calendar2.set(Calendar.MONTH, sysMonth - 1);
        int sysDays = calendar.getActualMaximum(Calendar.DATE);
        if (sysYearTimeNum > arriveYearDateNum) {
            return arr.substring(0, 10).replaceAll("-", "/");
        } else if ((arrMonth - sysMonth) > 1) {
            return arriveDate.substring(4, 6) + "/" + arriveDate.substring(6, 8);
        } else if ((arrMonth - sysMonth) == 1) {
            if (arrDay + sysDays - sysDay > 7) {
                return arriveDate.substring(4, 6) + "/" + arriveDate.substring(6, 8);
            } else if ((arrDay - sysDay) < 7 && (arrDay - sysDay) >= 1) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmm");
                long millionSeconds = sdf1.parse(arriveDate + "000000").getTime();//毫秒
                Date d = new Date(millionSeconds);
                return sdf.format(d);
            }
        } else {
            if ((arrDay - sysDay) < 7 && (arrDay - sysDay) >= 1) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmm");
                long millionSeconds = sdf1.parse(arriveDate + "000000").getTime();//毫秒
                Date d = new Date(millionSeconds);
                return sdf.format(d);
            } else if (arrDay == sysDay) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmm");
                Long l = new Long(time13);
                Date date = new Date(l);
                String today = sdf1.format(date).substring(8, 12);
                return today.substring(0, 2) + ":" + today.substring(2, 4);
            } else {
                return arriveDate.substring(4, 6) + "/" + arriveDate.substring(6, 8);
            }
        }
        return null;
    }

    /**
     * change the type of date from 2016年5月 to 2016-05
     */

}
