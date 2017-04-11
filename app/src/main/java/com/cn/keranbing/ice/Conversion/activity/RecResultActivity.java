package com.cn.keranbing.ice.Conversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cn.keranbing.ice.Conversion.adapter.HomeAdapter;
import com.cn.keranbing.ice.Conversion.adapter.RecResultAdapter;
import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.Conversion.bean.RecResult;
import com.cn.keranbing.ice.Conversion.views.FullyLinearLayoutManager;
import com.cn.keranbing.ice.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.appcompat.R.attr.title;

public class RecResultActivity extends AppCompatActivity {

    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvCenter)
    TextView tvCenter;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.ivContent)
    ImageView ivContent;
    @BindView(R.id.rcRecResult)
    RecyclerView recyclerView;


    private List<RecResult> datas;
    private RecResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity_rec_result);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        datas = new ArrayList<>();
        RecResult RecResult1 = new RecResult("单据类型:", "飞机票");
        RecResult RecResult2 = new RecResult("航空公司:", "中国南方航空");
        RecResult RecResult3 = new RecResult("航班:", "CZ9017");
        RecResult RecResult4 = new RecResult("到达站:", "北京");
        RecResult RecResult5 = new RecResult("序号:", "156");
        RecResult RecResult6 = new RecResult("日期:", "2017年3月1日");
        RecResult RecResult7 = new RecResult("姓名:", "诸葛亮");

        datas.add(RecResult1);
        datas.add(RecResult2);
        datas.add(RecResult3);
        datas.add(RecResult4);
        datas.add(RecResult5);
        datas.add(RecResult6);
        datas.add(RecResult7);

        tvContent.setText(getIntent().getStringExtra("content"));
        Glide.with(RecResultActivity.this)
                .load(new File(getIntent().getStringExtra("file")))
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(ivContent);

        tvCenter.setText("转换结果");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //实例化Adapter
        adapter = new RecResultAdapter(RecResultActivity.this, datas);

        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
        //把LayoutManager设置给RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecResultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, RecResult recResult) {
                Intent intent=new Intent(RecResultActivity.this,RecItemChoiceActivity.class);
                intent.putExtra("RecResult", recResult);
                startActivity(intent);
            }

        });


    }

}
