package com.leozkt.chameleonadapterlib;

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
    BaseItemBinder getItemViewBinder(int index) ;

    /**
     * Link the ItemViewBinder
     *
     * @param itemViewBinder
     */
     void link(BaseItemBinder itemViewBinder);

    /**
     * Return the type or the specific item
     *
     * @param item
     * @return
     */
    int returnItemType(Object item);

}
