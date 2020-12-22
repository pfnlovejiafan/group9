package com.example.p7mvp.base;

import android.app.Application;
import android.content.Context;

import java.util.Objects;

public class App extends Application {
    private static volatile Context context;

    @Override
    public void onCreate() {
        super.onCreate();
            context = this;
    }

    public static Context getContext(){
        if (context != null)
           return context;
        return null;
    }

    public static String getStr(int stringId){
        return Objects.requireNonNull(getContext()).getString(stringId);
    }
}
