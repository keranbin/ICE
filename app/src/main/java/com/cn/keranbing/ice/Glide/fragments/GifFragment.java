package com.cn.keranbing.ice.Glide.fragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.keranbing.ice.Glide.adapter.GifAdapter;
import com.cn.keranbing.ice.Glide.bean.Gif;
import com.cn.keranbing.ice.Glide.common.JsoupUtils;
import com.cn.keranbing.ice.Glide.views.LoadRecyclerView;
import com.cn.keranbing.ice.R;
import com.cn.keranbing.ice.Utils.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by keranbin on 2017/2/22.
 */

public class GifFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoadRecyclerView.OnLoadingListener {
 public static final String TAG="GifFragment";

    @BindView(R.id.glide_gif_fragment_rv)
    LoadRecyclerView rv;
    @BindView(R.id.glide_gif_fragment_sr)
    SwipeRefreshLayout sr;

    private View view;
    private String url;
    private int type;
    private ArrayList<Gif> lists=new ArrayList<>();

    private GifAdapter adapter;



    public static GifFragment newInstance(String url, int type) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putInt("type", type);
        GifFragment fragment = new GifFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.glide_gif_fragment, container, false);
        }

        ButterKnife.bind(this, view);
        sr.setColorSchemeResources(
                R.color.main,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light);
        sr.setOnRefreshListener(this);

        Bundle bundle=getArguments();
        url=bundle.getString("url");
        type=bundle.getInt("type");
        getData(url);
        return view;

    }

    /**
     * 获取数据
     * @param url
     */
    private void getData(final String url) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                lists= JsoupUtils.getGif(url,type);
                LogUtil.i(TAG, String.valueOf(lists.size()));
                if(lists.size()>0){
                    if(getActivity()!=null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI();
                            }
                        });
                    }
                }
            }
        }.start();
    }

    private void updateUI() {
        if(sr!=null){
            sr.setRefreshing(false);
        }

        if(adapter==null){
            adapter=new GifAdapter(getActivity(),lists);
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoading() {

    }

}
