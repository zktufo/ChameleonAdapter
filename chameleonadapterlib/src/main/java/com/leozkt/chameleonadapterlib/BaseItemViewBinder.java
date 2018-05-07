package com.leozkt.chameleonadapterlib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
public abstract class BaseItemViewBinder<T, VH extends RecyclerView.ViewHolder> {

    private int layoutId;
    private Class clazz;

    public BaseItemViewBinder(int layoutId, Class<T> clazz) {
        this.layoutId = layoutId;
        this.clazz = clazz;
    }

    public RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerViewBaseViewHolder(inflater.inflate(layoutId, parent, false));
    }


    public abstract void onBindViewHolder(VH holder, int position, T item);

    public Class<T> getItemClass() {
        return clazz;
    }

}
