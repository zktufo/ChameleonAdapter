package com.greentown.chameleonadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> {

    private int layoutId;

    public ItemViewBinder(int layoutId) {
        this.layoutId = layoutId;
    }

    public <VH> RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerViewBaseViewHolder(inflater.inflate(layoutId, parent, false));
    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

}
