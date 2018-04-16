package com.greentown.chameleonadapter;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
public interface TypeLinkPool {

    /**
     * Return the ItemViewBinder
     *
     * @param index
     * @return
     */
    ItemViewBinder getItemViewBinder(int index);

    /**
     * Link the ItemViewBinder with index
     *
     * @param itemViewBinder
     * @param index
     */
    <T> void link(ItemViewBinder itemViewBinder, Class<T> index);

    int indexOfType(Object item);

}
