package com.cn.keranbing.ice.Conversion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.Conversion.bean.RecResult;
import com.cn.keranbing.ice.Conversion.views.AlignTextView;
import com.cn.keranbing.ice.R;

import java.util.List;

/**
 * Created by keranbin on 2017/4/6.
 */

public class RecResultAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    //数据源
    private Context context;
    private List<RecResult> datas;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    //构造函数
    public RecResultAdapter(Context context, List<RecResult> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversion_item_rec_result, viewGroup, false);
        RecResultViewHolder vh = new RecResultViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        ((RecResultViewHolder) viewHolder).ivIcon.setBackgroundResource(R.mipmap.navigation_next_item_dark);
        ((RecResultViewHolder) viewHolder).ivIcon.setOnClickListener(this);
        ((RecResultViewHolder) viewHolder).tvLeft.setText(datas.get(position).getStrResultTitle());
        ((RecResultViewHolder) viewHolder).etRight.setText(datas.get(position).getStrResultContent());

        viewHolder.itemView.setTag(datas.get(position));
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (RecResult) v.getTag());
        }
    }

    public class RecResultViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private AlignTextView tvLeft;
        private TextView etRight;

        public RecResultViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivRight);
            tvLeft = (AlignTextView) itemView.findViewById(R.id.tvLeft);
            etRight = (EditText) itemView.findViewById(R.id.etRight);
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, RecResult recResult);
    }
}