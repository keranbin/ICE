package com.cn.keranbing.ice.Conversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cn.keranbing.ice.Conversion.adapter.HomeAdapter;
import com.cn.keranbing.ice.Conversion.adapter.RecResultAdapter;
import com.cn.keranbing.ice.Conversion.adapter.RecResultItemAdapter;
import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.Conversion.bean.RecResult;
import com.cn.keranbing.ice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by keranbin on 2017/4/6.
 */

public class RecItemChoiceActivity extends AppCompatActivity {
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvCenter)
    TextView tvCenter;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rc_rec_item_choice)
    RecyclerView recyclerView;

    private String strTitle;
    private ArrayList<String> listItems;

    private RecResultItemAdapter adapter;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity_rec_item_choice);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        RecResult recResult= (RecResult) getIntent().getSerializableExtra("RecResult");
        strTitle = getIntent().getStringExtra("Title");
//        listItems = getIntent().getStringArrayListExtra("listItems");
        tvCenter.setText(strTitle);
        //实例化Adapter
        String[] items = getResources().getStringArray(R.array.airline_company);
        adapter = new RecResultItemAdapter(RecItemChoiceActivity.this, items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //把LayoutManager设置给RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecResultItemAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String str) {
                intent = new Intent();
                intent.putExtra("Choice", str);
                setResult(0x123, intent);
                finish();
            }
        });
    }
}
