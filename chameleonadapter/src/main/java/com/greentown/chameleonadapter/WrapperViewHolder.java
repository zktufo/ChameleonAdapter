package com.greentown.chameleonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by kaituozheng on 2017/5/11.
 */

public class WrapperViewHolder extends RecyclerView.ViewHolder {


    private final Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;

    public WrapperViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static WrapperViewHolder createViewHolder(Context context, View itemView)
    {
        WrapperViewHolder holder = new WrapperViewHolder(context, itemView);
        return holder;
    }
}
