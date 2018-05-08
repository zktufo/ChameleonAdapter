package com.leozkt.chameleonadapterlib;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leozkt.chameleonadapterlib.exception.ResNotFoundException;

/**
 *
 * Created by kaituozheng on 2017/5/15.
 */

public class RecyclerViewBaseViewHolder extends RecyclerView.ViewHolder {
    public RecyclerViewBaseViewHolder(View itemView) {
        super(itemView);
    }

    public View getViewById(int resId) throws ResNotFoundException {

        View view = itemView.findViewById(resId);

        if (view != null) {
            return view;
        }

        throw new ResNotFoundException();

    }





}
