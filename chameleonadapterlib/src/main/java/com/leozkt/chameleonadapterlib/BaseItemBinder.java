package com.leozkt.chameleonadapterlib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
public interface BaseItemBinder<T, VH extends RecyclerView.ViewHolder> {

    RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    void onBindViewHolder(VH holder, int position, T item);

    Class<T> getItemClass();

}
