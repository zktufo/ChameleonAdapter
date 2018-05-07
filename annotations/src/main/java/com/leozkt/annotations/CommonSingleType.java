package com.leozkt.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
@Retention(CLASS)
@Target(FIELD)
public @interface CommonSingleType {
    int value();
}
