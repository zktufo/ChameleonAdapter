package com.leozkt.chameleonadapter.homepage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leozkt.chameleonadapterlib.BaseItemBinder;
import com.leozkt.chameleonadapterlib.RecyclerViewBaseViewHolder;
import com.leozkt.chameleonadapter.homepage.data_entity.SecondItemEntity;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public class SecondItemViewBinder implements BaseItemBinder {

    int layoutId;

    public SecondItemViewBinder(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerViewBaseViewHolder(inflater.inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item) {

    }


    @Override
    public Class<SecondItemEntity> getItemClass() {
        return SecondItemEntity.class;
    }


}
