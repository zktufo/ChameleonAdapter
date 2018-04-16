package com.greentown.chameleonadapter;

import android.util.SparseArray;

/**
 * Implementation class the TypeLinkPool
 *
 * @author zhengkaituo
 * @date 2018/4/9
 */
public class TypeLinkPoolImp implements TypeLinkPool {

    private SparseArray<ItemViewBinder> binderPools;

    public TypeLinkPoolImp() {
        binderPools = new SparseArray<>();
    }

    @Override
    public ItemViewBinder getItemViewBinder(int index) {
        return binderPools.get(index);
    }

    @Override
    public void link(ItemViewBinder itemViewBinder, Class index) {
        binderPools.put(index.hashCode(), itemViewBinder);
    }

    @Override
    public int indexOfType(Object item) {
        return 0;
    }


}
