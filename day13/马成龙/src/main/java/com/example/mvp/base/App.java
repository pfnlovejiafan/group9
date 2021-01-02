package com.example.mvp.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.sql.NClob;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    }

    public static Context context() {
        return mContext;
    }


}