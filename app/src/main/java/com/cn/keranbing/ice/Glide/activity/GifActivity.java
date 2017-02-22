package com.cn.keranbing.ice.Glide.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cn.keranbing.ice.Glide.adapter.PagerAdapter;
import com.cn.keranbing.ice.Glide.common.IPHelper;
import com.cn.keranbing.ice.Glide.fragments.GifFragment;
import com.cn.keranbing.ice.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by keranbin on 2017/2/22.
 */

public class GifActivity extends AppCompatActivity {


    @BindView(R.id.glide_main_activity_toolbar)
    Toolbar toolbar;
    @BindView(R.id.glide_main_activity_vp)
    ViewPager vp;
    @BindView(R.id.glide_main_activity_tab)
    TabLayout tab;

    private String str[][]=new String[][]{
            {"搞笑GIF", IPHelper.GLIDE_GIF_GAOXIAO},
            {"邪恶GIF", IPHelper.GLIDE_GIF_XIEE},
            {"搞笑图片", IPHelper.GLIDE_IMAGE_GAOXIAO}
    };

    private ArrayList<Fragment> lists=new ArrayList<>();
    private PagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_main_activity);
        ButterKnife.bind(this);

        inits();
    }

    private void inits() {
        //设置toolbar返回键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tab.setupWithViewPager(vp);
        tab.setTabMode(TabLayout.MODE_FIXED);

        //初始化数据
        for(int i=0;i<str.length;i++){
            lists.add(GifFragment.newInstance(str[i][1],i));
        }

        adapter=new PagerAdapter(getSupportFragmentManager(),lists,str);
        vp.setAdapter(adapter);


    }
}
