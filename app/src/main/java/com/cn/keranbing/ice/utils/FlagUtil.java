package com.cn.keranbing.ice.Utils;

/**
 * Created by keranbin on 2016/8/25.
 */
public class FlagUtil  {

    //出差申请选择项目名称request code
    public static final int CCSQ_SELECT_PROJECT_REQUEST_CODE=0X123;
    //出差申请选择项目名称result code
    public static final int CCSQ_SELECT_PROJECT_RESULT_CODE=0x123;

    //出差申请选择出差人request code
    public static final  int CCSQ_SELECT_APPLICATION_PERSONS_REQUEST_CODE=0x234;
    //出差申请选择出差人result code
    public static final  int CCSQ_SELECT_APPLICATION_PSERSONS_RESULT_CODE=0X234;

    //出差申请选择指派人request code
    public static final  int CCSQ_SELECT_ASSIGNS_PERSONS_REQUEST_CODE=0x345;
    //出差申请选择指派人result code
    public static final  int CCSQ_SELECT_ASSIGINS_PERSONS_RESULT_CODE=0x345;

    //出差申请选择费用承担部门request code
    public static final int CCSQ_SELECT_DEPT_REQUEST_CODE=0x456;
    //出差申请选择费用承担部门result code
    public static final int CCSQ_SELECT_DEPT_RESULT_CODE=0x456;

    //listView 滑动显示打开flag
    public static final int SWIPE_OPEN=0x123;
    //listView 滑动显示删除flag
    public static final  int SWIPE_DELETE=0x234;
    //listView 滑动显示调整flag
    public static final  int SWIPE_ADJUST=0x345;
    //listView 不能够滑动删除flag
    public static final int SWIPE_DELETE_FAIL=0;
    //listView 能够滑动删除flag
    public static final int SWIPE_DELETE_SUCCESS=1;



}
