package com.leozkt.chameleonadapterlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
public interface BaseItemBinder {

    /**
     * Create ViewHolder
     *
     * @param inflater
     * @param parent
     * @return
     */
    RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    /**
     * Binding the view event with ViewHolder
     *
     * @param holder
     * @param position
     * @param item
     */
    void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item);

    /**
     * Return the item class
     *
     * @return
     */
    Class getItemClass();

}
