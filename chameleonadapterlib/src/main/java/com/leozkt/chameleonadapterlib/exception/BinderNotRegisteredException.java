package com.leozkt.chameleonadapterlib.exception;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public class BinderNotRegisteredException extends RuntimeException {
    public BinderNotRegisteredException() {
        super("This binder was not registered into typepool");
    }
}
