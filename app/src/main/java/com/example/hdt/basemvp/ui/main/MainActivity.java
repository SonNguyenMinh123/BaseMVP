package com.example.hdt.basemvp.ui.main;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;

import com.example.hdt.basemvp.R;
import com.example.hdt.basemvp.ui.base.activity.BaseActivity;
import com.example.hdt.basemvp.ui.base.animation.ScreenAnimation;
import com.example.hdt.basemvp.ui.base.fragment.BaseFragment;
import com.example.hdt.basemvp.ui.main.listmusic.ListMusicFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void findViewByIds() {

    }

    @SuppressLint("CommitTransaction")
    @Override
    public void initComponents() {
        FragmentManager fManager = getSupportFragmentManager();
        BaseFragment.openFragment(fManager,
                fManager.beginTransaction(),
                ListMusicFragment.class,
                ScreenAnimation.NONE,
                null, false, true);
    }

    @Override
    public void setEvents() {

    }
}
