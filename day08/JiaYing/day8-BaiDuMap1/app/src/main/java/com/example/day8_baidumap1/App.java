package com.example.day8_baidumap1;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

public class App extends Application {
    //全局的Context  整个程序都可以用
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContext = this;
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    public static Context context() {
        return mContext;
    }

    public static String getStr(int p) {
        return App.context().getString(p);
    }
}
