package com.cn.keranbing.ice.Glide.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by keranbin on 2017/2/22.
 */

public class LoadRecyclerView extends RecyclerView {
    private LinearLayoutManager manager;
    private boolean isScroll=false;
    private OnLoadingListener onLoadingListener;


    public LoadRecyclerView(Context context) {
        super(context);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setOnLoadingListener(OnLoadingListener onLoadingListener){
        this.onLoadingListener=onLoadingListener;
    }


    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);

        if(manager==null){
            if(getLayoutManager()instanceof LinearLayoutManager){
                manager= (LinearLayoutManager) getLayoutManager();
            }
        }

        if(manager!=null&&onLoadingListener!=null){
            if(screenState==RecyclerView.SCROLL_STATE_IDLE&&isScroll){
                int lastVisibleItem=manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount=manager.getItemCount();
                if(lastVisibleItem==(totalItemCount-1)){
                    onLoadingListener.onLoading();
                    isScroll=false;
                }
            }
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if(dy>0){
            isScroll=true;
        }else{
            isScroll=false;
        }
    }

    public interface OnLoadingListener{
        void onLoading();
    }
}
