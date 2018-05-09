package com.leozkt.chameleonadapterlib;

import android.util.SparseArray;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
public interface TypeLinkPool {

    /**
     * Return the itemViewBinder
     *
     * @param index
     * @return
     */
    BaseItemBinder getItemViewBinder(int index);

    /**
     * Link the itemViewBinder
     *
     * @param itemViewBinder
     */
    void link(BaseItemBinder itemViewBinder);

    /**
     * Link the bindPools
     *
     * @param bindPools
     */
    void linkAll(SparseArray<BaseItemBinder> bindPools);

    /**
     * Return the type or the specific item
     *
     * @param item
     * @return
     */
    int returnItemType(Object item);

}
