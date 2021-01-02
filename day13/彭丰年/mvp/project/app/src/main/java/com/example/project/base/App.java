package com.example.project.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App extends Application {

    //全局的Content，整个程序都可以使用
    private static Context mContent;


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContent = this;

    }

    public static Context getContent() {
        return mContent;
    }

    public static String getStr(int p) {
        return App.mContent.getString(p);
    }

}
