package com.greentown.chameleonadapter.homepage;

import com.greentown.chameleonadapterlib.BaseItemViewBinder;
import com.greentown.chameleonadapterlib.RecyclerViewBaseViewHolder;
import com.greentown.chameleonadapterlib.data.FirstItemEntity;
import com.greentown.chameleonadapterlib.data.SecondItemEntity;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public class SecondItemViewBinder extends BaseItemViewBinder<SecondItemEntity, RecyclerViewBaseViewHolder> {


    public SecondItemViewBinder(int layoutId) {
        super(layoutId, SecondItemEntity.class);
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, SecondItemEntity item) {

    }


}
