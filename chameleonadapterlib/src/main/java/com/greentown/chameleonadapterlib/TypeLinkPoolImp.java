package com.greentown.chameleonadapterlib;

import android.util.SparseArray;

import com.greentown.chameleonadapterlib.exception.BinderNotRegisteredException;

/**
 * Implementation class of the TypeLinkPool
 *
 * @author zhengkaituo
 * @date 2018/4/9
 */
public class TypeLinkPoolImp implements TypeLinkPool {

    private SparseArray<BaseItemViewBinder> binderPools;


    public TypeLinkPoolImp() {
        binderPools = new SparseArray<>();
    }

    @Override
    public BaseItemViewBinder getItemViewBinder(int type) {
        if (binderPools.get(type) == null) {
            throw new BinderNotRegisteredException();
        }
        return binderPools.get(type);
    }

    /**
     * register the specific itemViewBinder int othe binderPool
     *
     * @param itemViewBinder
     */
    @Override
    public void link(BaseItemViewBinder itemViewBinder) {
        binderPools.put(itemViewBinder.getItemClass().hashCode(), itemViewBinder);
    }


    /**
     * @param item
     * @return the type code of item
     */
    @Override
    public int returnItemType(Object item) {
        return item.getClass().hashCode();
    }


}
