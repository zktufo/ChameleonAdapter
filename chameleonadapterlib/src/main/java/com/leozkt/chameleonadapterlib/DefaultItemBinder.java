package com.leozkt.chameleonadapterlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/5/8
 */
public final class DefaultItemBinder extends BaseItemBinder {

    int layoutId;
    Class clazz;
    OnBindListener mOnBindListener;

    public DefaultItemBinder(int layoutId, Class clazz) {
        this.layoutId = layoutId;
        this.clazz = clazz;
    }

    @Override
    public RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerViewBaseViewHolder(inflater.inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item) {
        if (mOnBindListener != null) {
            mOnBindListener.onBindViewHolder(holder, position, item);
        }
    }

    @Override
    public Class getItemClass() {
        return clazz;
    }

    @Override
    public void setOnBindListener(OnBindListener onBindListener) {
        this.mOnBindListener = onBindListener;
    }


}
