package com.cn.keranbing.ice.TimeLine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cn.keranbing.ice.R;
import com.cn.keranbing.ice.TimeLine.adapter.TimeLineAdapter;
import com.cn.keranbing.ice.TimeLine.bean.TimeLine;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by keranbin on 2017/3/22.
 */

public class TimeLineActivity extends AppCompatActivity {
    @BindView(R.id.rv_time_line_main_activity)
    RecyclerView recycleview;

    private ArrayList<TimeLine> lists;


    TimeLineAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_activity_main);
        ButterKnife.bind(this);
        initDatas();
        inits();
    }

    /**
     * 初始化數據
     */
    private void initDatas() {
        lists=new ArrayList<>();
        TimeLine timeLine=new TimeLine(0,"文本模式","天氣不錯，春遊去了");
        TimeLine timeLine1=new TimeLine(0,"文本模式","天氣不錯，春遊去了");
        TimeLine timeLine2=new TimeLine(1,"gif模式","http://www.zbjuran.com/uploads/allimg/170206/10-1F206135ZHJ.gif");
        TimeLine timeLine3=new TimeLine(1,"gif模式","http://www.zbjuran.com/uploads/allimg/170206/10-1F206135H35U.gif");
        TimeLine timeLine4=new TimeLine(2,"video模式","Video地址爲。。。。。");
        TimeLine timeLine5=new TimeLine(2,"video模式","Video地址爲。。。。。");
        lists.add(timeLine);
        lists.add(timeLine1);
        lists.add(timeLine2);
        lists.add(timeLine3);
        lists.add(timeLine4);
        lists.add(timeLine5);
    }

    private void inits() {
        adapter=new TimeLineAdapter(TimeLineActivity.this,lists);
        recycleview.setLayoutManager(new LinearLayoutManager(TimeLineActivity.this));
        recycleview.setAdapter(adapter);

    }
}
