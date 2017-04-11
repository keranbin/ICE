package com.cn.keranbing.ice.Conversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cn.keranbing.ice.Conversion.adapter.HomeAdapter;
import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvCenter)
    TextView tvCenter;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ryHome)
    RecyclerView recyclerView;


    private List<Home> datas;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity_home);
        ButterKnife.bind(this);
        //初始化页面
        initView();
        initDatas();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化Recycleview数据
     */
    private void initDatas() {
        datas = new ArrayList<>();
        Home Home1 = new Home(R.mipmap.mainico01, "飞机票");
        Home Home2 = new Home(R.mipmap.mainico02, "火车票");
        Home Home3 = new Home(R.mipmap.mainico03, "出租车票");
        Home Home4 = new Home(R.mipmap.mainico04, "住宿费");
        Home Home5 = new Home(R.mipmap.mainico05, "餐费");
        Home Home6 = new Home(R.mipmap.mainico06, "通讯费");
        Home Home7 = new Home(R.mipmap.mainico07, "办公用品");
        Home Home8 = new Home(R.mipmap.mainico08, "资料复印");
        Home Home9 = new Home(R.mipmap.mainico09, "其他");
        datas.add(Home1);
        datas.add(Home2);
        datas.add(Home3);
        datas.add(Home4);
        datas.add(Home5);
        datas.add(Home6);
        datas.add(Home7);
        datas.add(Home8);
        datas.add(Home9);

        //实例化Adapter
        adapter = new HomeAdapter(HomeActivity.this, datas);

        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        //把LayoutManager设置给RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Home home) {
                Intent intent=new Intent(HomeActivity.this,ImportDocActivity.class);
                intent.putExtra("HOME", home);
                startActivity(intent);
            }
        });
    }

}
