package com.example.hdt.basemvp.ui.base.fragment;

import com.example.hdt.basemvp.ui.base.IBasePresenter;

/**
 * Created by hdt
 */

public abstract class BaseMVPFragment<P extends IBasePresenter/*1*/> extends BaseFragment {
    protected P mPresenter;/*1*/

    @Override
    public void onDestroyView() {
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        super.onDestroyView();
    }
}
