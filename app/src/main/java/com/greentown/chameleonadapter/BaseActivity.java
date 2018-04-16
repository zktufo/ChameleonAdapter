package com.greentown.chameleonadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    public <T extends View> T $(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * return the layoutId of the content view
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * the initialization of the view
     */
    public abstract void initView();

    /**
     * set the data source of the layout
     */
    public abstract void initData();
}
