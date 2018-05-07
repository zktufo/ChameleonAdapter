package com.leozkt.chameleonadapter.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhengkaituo
 * @date 2018/4/16
 */
public abstract class BaseFragment extends Fragment {

    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutId(), container, false);
        initView();
        initData();
        return root;
    }


    public <T extends View> T $(int resId) {
        return (T) root.findViewById(resId);
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
