package com.leozkt.chameleonadapterlib.exception;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public  class ResNotFoundException extends RuntimeException {
    public ResNotFoundException() {
        super("There is no resource found");
    }
}
