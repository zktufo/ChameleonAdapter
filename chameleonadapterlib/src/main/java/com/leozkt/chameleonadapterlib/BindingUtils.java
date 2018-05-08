package com.leozkt.chameleonadapterlib;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Utils for generating the ItemBinder with specific layout&item class
 *
 * @author zhengkaituo
 * @date 2018/5/8
 */
public class BindingUtils {


    public static BaseItemBinder getBinder(int layoutId, Class clazz) {
        BaseItemBinder baseItemBinder = new ItemBindImpl(layoutId, clazz);
        return baseItemBinder;
    }


}
