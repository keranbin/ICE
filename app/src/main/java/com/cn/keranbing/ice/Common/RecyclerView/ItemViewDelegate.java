package com.cn.keranbing.ice.Common.RecyclerView;

/**
 * Created by keranbin on 2017/2/28.
 */

public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}