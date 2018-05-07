package com.leozkt.chameleonadapter.homepage;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
public class MultiTypePresenter implements HomepageContract.Presenter {
    private final HomepageContract.View mMultiTypeView;


    public MultiTypePresenter(HomepageContract.View multiTypeView) {
        mMultiTypeView = multiTypeView;
        mMultiTypeView.setPresenter(this);
    }

    @Override
    public void start() {
        mMultiTypeView.showList();
    }

    @Override
    public void refreshList() {

    }
}
