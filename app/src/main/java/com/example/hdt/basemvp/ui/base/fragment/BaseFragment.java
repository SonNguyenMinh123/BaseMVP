package com.example.hdt.basemvp.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hdt.basemvp.R;
import com.example.hdt.basemvp.ui.base.IViewMain;
import com.example.hdt.basemvp.ui.base.activity.BaseActivity;
import com.example.hdt.basemvp.ui.base.animation.ScreenAnimation;

import java.util.List;

/**
 * Created by hdt
 */

public abstract class BaseFragment extends Fragment implements IViewMain {
    protected boolean mIsDestroy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mIsDestroy = false;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewByIds();
        initComponents();   // Đổ dữ liệu vào View
        setEvents();
//        getView();  // Trả về View root của Fragment
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showProgressDialog() {
        if (mIsDestroy) {
            return;
        }
        getBaseActivity().showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        if (mIsDestroy) {
            return;
        }
        getBaseActivity().hideProgressDialog();
    }

    @Override
    public void showMessage(String content) {
        Toast.makeText(getBaseActivity(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int content) {
        Toast.makeText(getBaseActivity(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackRoot() {//(2)
        getBaseActivity().onBackMain();//(3)
    }

    @Override
    public void onDestroyView() {
        mIsDestroy = true;
        super.onDestroyView();
    }

    public static void openFragment(FragmentManager fManager,
                                    FragmentTransaction fTransaction,
                                    Class<? extends BaseFragment> classOpen,
                                    ScreenAnimation screenAnimation,
                                    Bundle data,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        BaseFragment baseFragment
                = (BaseFragment) fManager.findFragmentByTag(classOpen.getName());
        if (null == baseFragment) {
            //noinspection TryWithIdenticalCatches
            try {
                baseFragment = classOpen.newInstance();
                fTransaction.setCustomAnimations(
                        screenAnimation.getmEnterToRight(),
                        screenAnimation.getmExitToRight(),
                        screenAnimation.getmEnterToLeft(),
                        screenAnimation.getmExitToLeft());
                fTransaction.add(R.id.content, baseFragment, classOpen.getName());
                baseFragment.setArguments(data);//  Set data cho Fragment
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (baseFragment.isVisible()) {
                return;
            }
            fTransaction.setCustomAnimations(
                    screenAnimation.getmEnterToRight(),
                    screenAnimation.getmExitToRight(),
                    screenAnimation.getmEnterToLeft(),
                    screenAnimation.getmExitToLeft());
            fTransaction.show(baseFragment);
        }
        if (isAddBackStack) {
            fTransaction.addToBackStack(classOpen.getName());
        }
        if (isCommit) {
            fTransaction.commit();// Đóng fragmentTransaction
        }
    }

    public static void hideFragment(FragmentManager fManager,
                                    FragmentTransaction fTransaction,
                                    Class<? extends BaseFragment> classHide,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        BaseFragment baseFragment
                = (BaseFragment) fManager.findFragmentByTag(classHide.getName());
        if (null != baseFragment && baseFragment.isVisible()) {
            fTransaction.setCustomAnimations(
                    screenAnimation.getmEnterToRight(),
                    screenAnimation.getmExitToRight(),
                    screenAnimation.getmEnterToLeft(),
                    screenAnimation.getmExitToLeft());
            fTransaction.hide(baseFragment);
            if (isAddBackStack) {
                fTransaction.addToBackStack(classHide.getName());
            }
            if (isCommit) {
                fTransaction.commit();// Đóng fragmentTransaction
            }
        }
    }

    public static void openFragment(FragmentTransaction fTransaction,
                                    BaseFragment baseFragment,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        fTransaction.setCustomAnimations(
                screenAnimation.getmEnterToRight(),
                screenAnimation.getmExitToRight(),
                screenAnimation.getmEnterToLeft(),
                screenAnimation.getmExitToLeft());
        fTransaction.add(R.id.content, baseFragment, baseFragment.getClass().getName());
        if (isAddBackStack) {
            fTransaction.addToBackStack(baseFragment.getClass().getName());
        }
        if (isCommit) {
            fTransaction.commit();// Đóng fragmentTransaction
        }
    }

    public static void hideFragment(FragmentTransaction fTransaction,
                                    BaseFragment baseFragment,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        if (null != baseFragment && baseFragment.isVisible()) {
            fTransaction.setCustomAnimations(
                    screenAnimation.getmEnterToRight(),
                    screenAnimation.getmExitToRight(),
                    screenAnimation.getmEnterToLeft(),
                    screenAnimation.getmExitToLeft());
            fTransaction.hide(baseFragment);
            if (isAddBackStack) {
                fTransaction.addToBackStack(baseFragment.getClass().getName());
            }
            if (isCommit) {
                fTransaction.commit();// Đóng fragmentTransaction
            }
        }
    }

    public static BaseFragment getCurrentBaseFragment(FragmentManager fManager) {
        //noinspection RestrictedApi
        List<Fragment> fragmentList = fManager.getFragments();
        if (null == fragmentList) {
            return null;
        }
        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            BaseFragment baseFragment = (BaseFragment) fragmentList.get(i);
            if (null != baseFragment && baseFragment.isVisible()) {
                return baseFragment;
            }
        }
        return null;
    }
}