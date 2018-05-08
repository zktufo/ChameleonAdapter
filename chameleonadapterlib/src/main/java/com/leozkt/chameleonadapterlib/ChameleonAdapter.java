package com.leozkt.chameleonadapterlib;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Easy way to add Header/Footer or register various item type
 *
 * @author zhengkaituo
 * @date 2018/4/9
 */
public class ChameleonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private static Map<Class<?>, Constructor> BINDINGS = new HashMap();
    private static boolean debug = false;
    private static final String TAG = "ChameleonAdapter";

    public static void setDebug(boolean debug) {
        ChameleonAdapter.debug = debug;
    }

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
        BaseItemBinder binder = typeLinkPool.getItemViewBinder(viewType);
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
        typeLinkPool = new TypeLinkPoolImp();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isFooterView(position)) {
            return;
        } else if (isHeaderView(position)) {
            return;
        }
        BaseItemBinder itemViewPresenter = typeLinkPool.getItemViewBinder(holder.getItemViewType());
        RecyclerViewBaseViewHolder baseHolder = (RecyclerViewBaseViewHolder) holder;
        itemViewPresenter.onBindViewHolder(baseHolder, position, items.get(holder.getAdapterPosition() - getHeadersCount()));
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }

        Object item = items.get(position);

        return typeLinkPool.returnItemType(item);
    }

    public void link(BaseItemBinder itemViewBinder) {
        typeLinkPool.link(itemViewBinder);
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


    public static Unbinder bind(Object target) {
        Class<?> targetClass = target.getClass();
        if (debug) {
            Log.d(TAG, "Looking up itemViewBinding for " + targetClass.getName());
        }
        Constructor<? extends Unbinder> constructor = findBindingConstructorForClass(targetClass);

        if (constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException("Unable to create binding instance.", cause);
        }
    }

    private static Constructor findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Unbinder> bindingCtor = BINDINGS.get(cls);
        if (bindingCtor != null) {
            if (debug) {
                Log.d(TAG, "HIT: Cached in binding map.");
            }
            return bindingCtor;
        }
        String clsName = cls.getName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
            if (debug) {
                Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            }
            return null;
        }
        try {
            Class<?> bindingClass = cls.getClassLoader().loadClass(clsName + "_ItemViewBinding");
            //noinspection unchecked
            bindingCtor = (Constructor<? extends Unbinder>) bindingClass.getConstructor(cls);
            if (debug) {
                Log.d(TAG, "HIT: Loaded binding class and constructor.");
            }
        } catch (ClassNotFoundException e) {
            if (debug) {
                Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }
}



