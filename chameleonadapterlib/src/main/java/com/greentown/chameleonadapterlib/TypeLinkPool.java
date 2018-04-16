package com.greentown.chameleonadapterlib;

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
    BaseItemViewBinder getItemViewBinder(int index) ;

    /**
     * Link the ItemViewBinder
     *
     * @param itemViewBinder
     */
    <T> void link(BaseItemViewBinder itemViewBinder);

    /**
     * Return the type or the specific item
     *
     * @param item
     * @return
     */
    int returnItemType(Object item);

}
