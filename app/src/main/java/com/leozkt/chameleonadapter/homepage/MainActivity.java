package com.leozkt.chameleonadapter.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leozkt.annotations.BindItem;
import com.leozkt.chameleonadapter.R;
import com.leozkt.chameleonadapter.base.BaseActivity;
import com.leozkt.chameleonadapter.homepage.data_entity.FirstItemEntity;
import com.leozkt.chameleonadapter.homepage.data_entity.SecondItemEntity;
import com.leozkt.chameleonadapterlib.BaseItemBinder;
import com.leozkt.chameleonadapterlib.ChameleonAdapter;
import com.leozkt.chameleonadapterlib.data.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengkaituo
 */
public class MainActivity extends BaseActivity implements HomepageContract.View {

    RecyclerView homepageList;
    @BindItem(value = FirstItemEntity.class, layout = R.layout.item_first_layout)
    BaseItemBinder mThirdItemBinder;

    @BindItem(value = SecondItemEntity.class, layout = R.layout.item_second_layout)
    BaseItemBinder mFourthItemBinder;


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
        mAdapter = ChameleonAdapter.with(this);
        mAdapter.setItems(mDatas);
        mAdapter.link(mThirdItemBinder);
        mAdapter.link(mFourthItemBinder);
        homepageList.setAdapter(mAdapter);
        new MultiTypePresenter(this);
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
