package com.example.hdt.basemvp.common;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by hdt
 */

//    Application luôn sống cùng app
public class MVPApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context mContextApp; //  Việc có static ở khai báo biến chỉ có lợi cho phương thức getmContextApp()

    @Override
    public void onCreate() {
        super.onCreate();
        mContextApp = this;
    }

    public static Context getmContextApp() {
        return mContextApp;
    }
}