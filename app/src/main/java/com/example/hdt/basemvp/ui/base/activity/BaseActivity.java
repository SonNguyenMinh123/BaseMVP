package com.example.hdt.basemvp.ui.base.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hdt.basemvp.R;
import com.example.hdt.basemvp.ui.base.IViewMain;
import com.example.hdt.basemvp.ui.base.fragment.BaseFragment;

/**
 * Created by hdt
 */

//    tạo BaseActivity là Activity cha của tất cả các Activity trong app
public abstract class BaseActivity extends AppCompatActivity
        implements IViewMain {
    private ProgressDialog mProgressDialog;
    protected boolean mIsDestroy;// kiểm tra activity đã chết hay chưa

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDestroy = false;
        setContentView(getLayout());

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.Loading));

        findViewByIds();
        initComponents();   // Đổ dữ liệu vào View
        setEvents();
    }

    @Override
    public void showProgressDialog() {
        if (mIsDestroy) {
            return;
        }
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mIsDestroy) {
            return;
        }
        mProgressDialog.hide();
    }

    @Override
    public void showMessage(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        Vào khi ấn nút Back
        onBackRoot(); //(1)
    }

    @Override
    public void onBackRoot() {//(1)
        BaseFragment baseFragment = BaseFragment.getCurrentBaseFragment(getSupportFragmentManager());
        if (null != baseFragment) {
            baseFragment.onBackRoot();//(2)
        }
    }

    public final void onBackMain() {//(3)
//        Các đối tượng extend không override được
//        onBackPressed();//(1) => vòng lặp vô hạn
        super.onBackPressed();// Phá vỡ vòng lặp vô hạn
//
    }

    @Override
    protected void onDestroy() {
        mIsDestroy = true;
        super.onDestroy();
    }
}