package com.greentown.chameleonadapter.homepage;

import com.greentown.chameleonadapterlib.BaseItemViewBinder;
import com.greentown.chameleonadapterlib.RecyclerViewBaseViewHolder;
import com.greentown.chameleonadapterlib.data.FirstItemEntity;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public class FirstItemViewBinder extends BaseItemViewBinder<FirstItemEntity, RecyclerViewBaseViewHolder> {

    public FirstItemViewBinder(int layoutId, Class<FirstItemEntity> clazz) {
        super(layoutId, clazz);
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, FirstItemEntity item) {

    }


}
