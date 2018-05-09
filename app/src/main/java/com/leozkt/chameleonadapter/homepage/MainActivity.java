package com.leozkt.chameleonadapter.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.leozkt.annotations.BindItem;
import com.leozkt.chameleonadapter.R;
import com.leozkt.chameleonadapter.base.BaseActivity;
import com.leozkt.chameleonadapter.homepage.data_entity.FirstItemEntity;
import com.leozkt.chameleonadapter.homepage.data_entity.SecondItemEntity;
import com.leozkt.chameleonadapterlib.BaseItemBinder;
import com.leozkt.chameleonadapterlib.ChameleonAdapter;
import com.leozkt.chameleonadapterlib.DefaultItemBinder;
import com.leozkt.chameleonadapterlib.RecyclerViewBaseViewHolder;
import com.leozkt.chameleonadapterlib.data.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengkaituo
 */
public class MainActivity extends BaseActivity implements HomepageContract.View {

    RecyclerView homepageList;
    @BindItem(value = FirstItemEntity.class, layout = R.layout.item_first_layout)
    DefaultItemBinder mFirstItemBinder;


    ChameleonAdapter mAdapter;
    List<BaseEntity> mDatas = new ArrayList();
    private HomepageContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();

    }

    @Override
    public void initView() {
        homepageList = $(R.id.homepage_list);
        homepageList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        TextView textView = new TextView(this);
        textView.setText("this is header view");
        mAdapter = new ChameleonAdapter(this);
        mAdapter.setItems(mDatas);
        mAdapter.link(mFirstItemBinder);
        mAdapter.link(new SecondItemViewBinder(R.layout.item_second_layout));
        mAdapter.addHeaderView(textView);
        homepageList.setAdapter(mAdapter);
        new MultiTypePresenter(this);

        mFirstItemBinder.setOnBindListener(new BaseItemBinder.OnBindListener() {
            @Override
            public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item) {
                TextView txtFirst = (TextView) holder.getViewById(R.id.txt_first);
                txtFirst.setText("haha" + position);

            }
        });
    }


    @Override
    public void setPresenter(HomepageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showList() {
        for (int index = 0; index < 10; index++) {
            if (index % 2 == 0) {
                mDatas.add(new FirstItemEntity());
            } else {
                mDatas.add(new SecondItemEntity());
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isActive() {
        return isFinishing();
    }
}
