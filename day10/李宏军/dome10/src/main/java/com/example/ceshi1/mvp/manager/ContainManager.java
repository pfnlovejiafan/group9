package com.example.ceshi1.mvp.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.ceshi1.mvp.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainManager {
    private volatile static ContainManager mManager;
    //用来两个类之间传递对象
    private Map<String, Object> mMap;
    //定义一个集合存储所有需要添加到集合的Activity
    private Set<Activity> mSet;
    //全局的SP 整个程序都可以用
    private SharedPreferences mSp;

    private ContainManager() {
        if (mMap == null) {
            mMap = new HashMap<String, Object>();
        }
        if (mSet == null) {
            mSet = new HashSet<Activity>();
        }
        if (mSp == null) {
            mSp = App.context().getSharedPreferences("config",
                    App.MODE_PRIVATE);
        }
    }

    public void save_str(String key, String value) {
        mSp.edit().putString(key, value).commit();
    }

    public String get_str(String key) {
        return mSp.getString(key, "");
    }

    public void save_boolean(String key, Boolean value) {
        mSp.edit().putBoolean(key, value).commit();
    }

    public boolean get_boolean(String key) {
        return mSp.getBoolean(key, false);
    }


    public void addActivity(Activity activity) {
        mSet.add(activity);
    }


    public void clearActivity() {
        for (Activity activity : mSet) {
            if (activity != null) {
                activity.finish();
                System.exit(0);
            }
        }
    }

    public void putObj(String key, Object obj) {
        mMap.put(key, obj);
    }

    public  Object getObj(String key) {
        Object object = mMap.get(key);
        return object;
    }


    public static synchronized ContainManager getmManager() {
        if (mManager == null) {
            synchronized (ContainManager.class) {
                if (mManager == null) {
                    mManager = new ContainManager();
                }
            }

        }
        return mManager;
    }

}