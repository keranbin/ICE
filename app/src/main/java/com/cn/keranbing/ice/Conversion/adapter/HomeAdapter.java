package com.cn.keranbing.ice.Conversion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.R;

import java.util.List;

/**
 * Created by keranbin on 2017/3/30.
 */

public class HomeAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    //数据源
    private Context context;
    private List<Home> datas;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;



    //构造函数
    public HomeAdapter(Context context,List<Home> datas) {
        this.context=context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversion_item_home, viewGroup, false);
        HomeViewHolder vh = new HomeViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((HomeViewHolder) viewHolder).ivTitle.setBackgroundResource(datas.get(position).getIcon());
        ((HomeViewHolder) viewHolder).tvTitle.setText(datas.get(position).getTitle());
        viewHolder.itemView.setTag(datas.get(position));
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    /**
     * 总条目数量是数据源数量+1，因为我们有个Header
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Home) v.getTag());
        }
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivTitle;
        private TextView tvTitle;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ivTitle = (ImageView) itemView.findViewById(R.id.ivTitle);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Home home);
    }
}