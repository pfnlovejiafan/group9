package com.example.myapplication.bean;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        context=this;
    }

    public static Context context() {
        return context;
    }
    public static String getStr(int p){
        return App.context().getString(p);
    }
}
