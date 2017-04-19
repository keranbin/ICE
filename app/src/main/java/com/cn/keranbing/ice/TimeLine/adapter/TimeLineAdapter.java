package com.cn.keranbing.ice.TimeLine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cn.keranbing.ice.R;
import com.cn.keranbing.ice.TimeLine.bean.TimeLine;

import java.util.List;

/**
 * Created by keranbin on 2017/3/22.
 */

public class TimeLineAdapter extends RecyclerView.Adapter{
    private List<TimeLine> datas;
    private Context context;

    public enum Item_Type {
        RECYCLEVIEW_ITEM_TYPE_1,
        RECYCLEVIEW_ITEM_TYPE_2,
        RECYCLEVIEW_ITEM_TYPE_3
    }

    public TimeLineAdapter(Context context, List<TimeLine> datas) {
        this.datas = datas;
        this.context = context;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_1.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.timeline_main_recycleview_item_text, null);
            ViewHolderText viewHolder = new ViewHolderText(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_2.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.timeline_main_recycleview_item_gif, null);
            ViewHolderGif viewHolder = new ViewHolderGif(mView);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLEVIEW_ITEM_TYPE_3.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.timeline_main_recycleview_item_video, null);
            ViewHolderVideo viewHolder = new ViewHolderVideo(mView);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderText) {
            ((ViewHolderText) holder).tvTitle.setText(datas.get(position).getTitle() + "------文字样式一");
        } else if (holder instanceof ViewHolderGif) {
            ((ViewHolderGif) holder).tvTitle.setText(datas.get(position).getTitle() + "------gif样式二");
            Glide.with(context)
                    .load(datas.get(position).getDetail())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .into(((ViewHolderGif) holder).ivGif);
        } else if (holder instanceof ViewHolderVideo) {
            ((ViewHolderVideo) holder).tvTitle.setText(datas.get(position).getTitle() + "------video样式三");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).getType() == 0) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_1.ordinal();
        } else if (datas.get(position).getType() == 1) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_2.ordinal();
        } else if (datas.get(position).getType() == 2) {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_3.ordinal();
        }
        return -1;
    }


    class ViewHolderText extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvGifUrl;
        public ViewHolderText(View itemView) {
            super(itemView);
            tvTitle= (TextView) itemView.findViewById(R.id.tvTextTitle);
        }
    }

    class ViewHolderGif extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView ivGif;
        public ViewHolderGif(View itemView) {
            super(itemView);
            tvTitle= (TextView) itemView.findViewById(R.id.tvGifTitle);
            ivGif= (ImageView) itemView.findViewById(R.id.ivGifUrl);
        }
    }

    class ViewHolderVideo extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView ivVideo;
        public ViewHolderVideo(View itemView) {
            super(itemView);
            tvTitle= (TextView) itemView.findViewById(R.id.tvVideoTitle);
        }
    }
}
