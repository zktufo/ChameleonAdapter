package com.leozkt.annotations;

import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * @author zhengkaituo
 *
 */
@Retention(CLASS)
@Target(FIELD)
public @interface BindItem {
    Class value();

    @IdRes int layout();
}
