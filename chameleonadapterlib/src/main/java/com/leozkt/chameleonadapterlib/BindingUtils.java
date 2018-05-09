package com.leozkt.chameleonadapterlib;

/**
 * Utils for generating the ItemBinder with specific layout&item class
 *
 * @author zhengkaituo
 * @date 2018/5/8
 */
public class BindingUtils {


    public static DefaultItemBinder getBinder(int layoutId, Class clazz) {
        DefaultItemBinder baseItemBinder = new DefaultItemBinder(layoutId, clazz);
        return baseItemBinder;
    }


}
