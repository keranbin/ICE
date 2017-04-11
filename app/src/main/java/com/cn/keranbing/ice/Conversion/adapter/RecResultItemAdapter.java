package com.cn.keranbing.ice.Conversion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.keranbing.ice.R;

import java.util.List;

/**
 * Created by keranbin on 2017/4/6.
 */

public class RecResultItemAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    //数据源
    private Context context;
    private String[] datas;

    private RecResultItemAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;


    //构造函数
    public RecResultItemAdapter(Context context, String[] datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversion_item_rec_item_choice, viewGroup, false);
        RecResultItemViewHolder vh=new RecResultItemViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((RecResultItemAdapter.RecResultItemViewHolder) viewHolder).tvChoiceItem.setText(datas[position]);
        viewHolder.itemView.setTag(datas[position]);
    }


    public void setOnItemClickListener(RecResultItemAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public class RecResultItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChoiceItem;

        public RecResultItemViewHolder(View itemView) {
            super(itemView);
            tvChoiceItem = (TextView) itemView.findViewById(R.id.tvChoiceItem);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String str);
    }

}