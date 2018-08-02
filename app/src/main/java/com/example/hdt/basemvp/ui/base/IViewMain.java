package com.example.hdt.basemvp.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by hdt
 */

public interface IViewMain {
    int getLayout();

    void findViewByIds();

    void initComponents();

    void setEvents();

    void showProgressDialog();

    void hideProgressDialog();

    void showMessage(String content);

    void showMessage(@StringRes int content);
//  @StringRes: tạo gợi ý: truyền vào 1 int, vd: R.string.Content

    void onBackRoot();
}