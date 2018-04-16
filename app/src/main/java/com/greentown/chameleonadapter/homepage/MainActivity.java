package com.greentown.chameleonadapter.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.greentown.chameleonadapter.BaseActivity;
import com.greentown.chameleonadapter.R;
import com.greentown.chameleonadapterlib.ChameleonAdapter;
import com.greentown.chameleonadapterlib.data.BaseEntity;
import com.greentown.chameleonadapterlib.data.FirstItemEntity;
import com.greentown.chameleonadapterlib.data.SecondItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengkaituo
 */
public class MainActivity extends BaseActivity {

    TextView txtHello;

    RecyclerView homepageList;
//    @BindItem(value = HomePageEntity.class, layout = R.layout.activity_main)

    ChameleonAdapter mAdapter;
    List<BaseEntity> mDatas = new ArrayList();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
        mAdapter.link(new FirstItemViewBinder(R.layout.item_first_layout, FirstItemEntity.class));
        mAdapter.link(new SecondItemViewBinder(R.layout.item_second_layout));

        homepageList.setAdapter(mAdapter);

        for (int index = 0; index < 10; index++) {
            if (index % 2 == 0) {
                mDatas.add(new FirstItemEntity());
            } else {
                mDatas.add(new SecondItemEntity());
            }
        }

    }


}
