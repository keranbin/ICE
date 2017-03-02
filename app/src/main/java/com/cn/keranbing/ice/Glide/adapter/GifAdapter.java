package com.cn.keranbing.ice.Glide.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cn.keranbing.ice.Glide.bean.Gif;
import com.cn.keranbing.ice.R;

import java.util.ArrayList;

/**
 * Created by keranbin on 2017/2/22.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifHolder>{
    public Context context;
    public LayoutInflater inflater;
    public ArrayList<Gif> lists=new ArrayList<>();

    public GifAdapter(FragmentActivity context, ArrayList<Gif> lists) {
        this.context=context;
        this.lists=lists;
        inflater=LayoutInflater.from(context);
    }





    @Override
    public GifHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GifHolder vh=null;
        vh= new GifHolder(inflater.inflate(R.layout.glide_gif_recycleview_item, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(GifHolder holder, int position) {
        if(holder!=null){
            holder.tv.setText(lists.get(position).getTitle());
            Glide.with(context)
                    .load(lists.get(position).getUrl())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .into(holder.iv);
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class GifHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;

        public GifHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
