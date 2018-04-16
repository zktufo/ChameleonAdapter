package com.greentown.chameleonadapter;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
public class ChameleonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private final Context mContext;

    private List<?> items;
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    TypeLinkPool typeLinkPool;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            WrapperViewHolder holder = WrapperViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;

        } else if (mFootViews.get(viewType) != null) {
            WrapperViewHolder holder = WrapperViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinder<?, ?> binder = typeLinkPool.getItemViewBinder(viewType);
        return binder.onCreateViewHolder(inflater, parent);
    }

    /**
     * Set the data source of list
     *
     * @param items
     */
    public void setItems(List<?> items) {
        this.items = items;
    }

    public ChameleonAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isFooterView(position)) {
            return;
        } else if (isHeaderView(position)) {
            return;
        }
        Object item = items.get(holder.getAdapterPosition());
        ItemViewBinder itemViewPresenter = typeLinkPool.getItemViewBinder(holder.getItemViewType());
        itemViewPresenter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }

        Object item = items.get(position);

        return typeLinkPool.indexOfType(item);
    }

    public void link(Class clazz, ItemViewBinder itemViewBinder) {
        typeLinkPool.link(itemViewBinder, clazz);
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getRealItemCount() + getFootersCount();
    }

    /**
     * Return the real count of items
     *
     * @return
     */
    public int getRealItemCount() {
        return items.size();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * Add the HeaderView for RecyclerView
     *
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * Add the FooterView for RecyclerView
     *
     * @param view
     */
    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    /**
     * Whether the item is header view
     *
     * @param position
     * @return
     */
    private boolean isHeaderView(int position) {
        return position < getHeadersCount();
    }

    /**
     * Whether the item is footer view
     *
     * @param position
     * @return
     */
    private boolean isFooterView(int position) {
        return position >= getHeadersCount() + getRealItemCount() && position < getItemCount();

    }

    public int getFootersCount() {
        return mFootViews.size();
    }


    /**
     * Construct the instance of ChameleonAdapter
     *
     * @param context
     * @return
     */
    public static ChameleonAdapter with(Context context) {
        return new ChameleonAdapter(context);
    }


}
