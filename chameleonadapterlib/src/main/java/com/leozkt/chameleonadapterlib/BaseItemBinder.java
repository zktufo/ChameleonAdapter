package com.leozkt.chameleonadapterlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
public abstract class BaseItemBinder {
    OnBindListener mOnBindListener;

    /**
     * Create ViewHolder
     *
     * @param inflater
     * @param parent
     * @return
     */
    public abstract RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    /**
     * Binding the view event with ViewHolder
     *
     * @param holder
     * @param position
     * @param item
     */
    public abstract void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item);

    /**
     * Return the item class
     *
     * @return
     */
    public abstract Class getItemClass();

    public void setOnBindListener(OnBindListener onBindListener) {
        this.mOnBindListener = onBindListener;
    }

    public interface OnBindListener {
        void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item);
    }

}
