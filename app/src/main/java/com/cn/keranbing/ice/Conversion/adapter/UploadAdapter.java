package com.cn.keranbing.ice.Conversion.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.Conversion.utils.GetScreenSize;
import com.cn.keranbing.ice.R;

import java.io.File;
import java.util.List;

/**
 * Created by keranbin on 2017/3/31.
 */

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.UploadViewHolder> implements View.OnClickListener {
    //数据源
    private Context context;
    private List<String> datas;

    private UploadAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //构造函数
    public UploadAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversion_item_upload, viewGroup, false);
        UploadViewHolder vh = new UploadViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }


    @Override
    public void onBindViewHolder(UploadViewHolder viewHolder, int position) {
        if (position == datas.size()) {
            Glide.with(context)
                    .load(R.mipmap.add)
                    .centerCrop()
                    .crossFade()
                    .into(viewHolder.ivUpload);
        } else {
            Glide.with(context)
                    .load(new File(datas.get(position)))
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .into(viewHolder.ivUpload);
        }
    }


    public void setOnItemClickListener(UploadAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Home) v.getTag());
        }
    }

    public class UploadViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivUpload;

        public UploadViewHolder(View itemView) {
            super(itemView);
            ivUpload = (ImageView) itemView.findViewById(R.id.ivUpload);
            ViewGroup.LayoutParams lp =  ivUpload.getLayoutParams();
            lp.width = GetScreenSize.getScreenWidth(context)/3;
            lp.height=  lp.width;
            ivUpload.setLayoutParams(lp);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Home home);
    }
}
