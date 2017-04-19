package com.cn.keranbing.ice.TimeLine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.keranbing.ice.R;
import com.cn.keranbing.ice.TimeLine.bean.Personal;

import java.util.List;

/**
 * Created by keranbin on 2017/3/29.
 */

public class PersonalAdapter extends RecyclerView.Adapter {
    private List<Personal> datas;
    private Context context;

    public PersonalAdapter(Context context, List<Personal> datas) {
        this.datas = datas;
        this.context = context;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(context).inflate(R.layout.timeline_item_personal, parent,false);
            ViewHolderPer viewHolder = new ViewHolderPer(mView);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolderPer) holder).tvTitle.setText(datas.get(position).getStrTitle());

    }

    @Override
    public int getItemCount() {
        Log.i("adapter",datas.size()+"");
        return datas.size();
    }

    class ViewHolderPer extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvSubTitle;
        public ViewHolderPer(View itemView) {
            super(itemView);
            tvTitle= (TextView) itemView.findViewById(R.id.tvTextTitle);
        }
    }
}
