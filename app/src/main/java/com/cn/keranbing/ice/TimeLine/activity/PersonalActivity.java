package com.cn.keranbing.ice.TimeLine.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cn.keranbing.ice.R;
import com.cn.keranbing.ice.TimeLine.adapter.PersonalAdapter;
import com.cn.keranbing.ice.TimeLine.bean.Personal;
import com.cn.keranbing.ice.TimeLine.views.RyLinearLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalActivity extends AppCompatActivity {
    private ArrayList<Personal> lists;
    private PersonalAdapter adaper;

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rcPersonal)
    RecyclerView rcPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_activity_personal);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置toolbar高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.getLayoutParams().height = getAppBarHeight();
            toolbar.setPadding(toolbar.getPaddingLeft(), getStatusBarHeight(), toolbar.getPaddingRight(), toolbar.getPaddingBottom());
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initDatas();
        inits();
    }
    private void initDatas() {
        lists = new ArrayList<>();
        Personal personal1 = new Personal("姓名", "张三");
        Personal personal2 = new Personal("性别", "男");
        Personal personal3 = new Personal("爱好", "羽毛球");
        Personal personal4 = new Personal("姓名", "张三");
        lists.add(personal1);
        lists.add(personal2);
        lists.add(personal3);
        lists.add(personal4);
        lists.add(personal2);
    }

    private void inits() {
        adaper=new PersonalAdapter(PersonalActivity.this,lists);
//        rcPersonal.setLayoutManager(new RyLinearLayoutManager(getBaseContext() , LinearLayoutManager.VERTICAL, true));
        rcPersonal.setLayoutManager(new LinearLayoutManager(PersonalActivity.this));
        rcPersonal.setNestedScrollingEnabled(false);
        rcPersonal.setAdapter(adaper);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            PersonalActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getAppBarHeight() {
        return dip2px(56) + getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}