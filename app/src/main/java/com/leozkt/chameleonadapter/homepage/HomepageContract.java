package com.leozkt.chameleonadapter.homepage;

import com.leozkt.chameleonadapter.base.BasePresenter;
import com.leozkt.chameleonadapter.base.BaseView;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
public class HomepageContract {
    interface View extends BaseView<Presenter> {
        void showList();
        boolean isActive();
    }


    interface Presenter extends BasePresenter {
        void refreshList();

    }
}
