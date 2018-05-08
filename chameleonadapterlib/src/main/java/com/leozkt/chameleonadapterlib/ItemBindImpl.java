package com.leozkt.chameleonadapterlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/5/8
 */
public class ItemBindImpl implements BaseItemBinder {

    int layoutId;
    Class clazz;

    public ItemBindImpl(int layoutId, Class clazz) {
        this.layoutId = layoutId;
        this.clazz = clazz;
    }

    @Override
    public RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerViewBaseViewHolder(inflater.inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item) {

    }

    @Override
    public Class getItemClass() {
        return clazz;
    }


}
