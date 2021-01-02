package com.example.project.manager;

import android.app.Activity;
import android.content.SharedPreferences;


import com.example.project.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GlobalManager {
    private volatile static GlobalManager mManager;

    //用来两个类之间传递对象
    private static Map<String, Object> mMap;
    //定义一个集合，用来存储所有需要添加到集合的Activity
    private static Set<Activity> mSet;
    //全局的sp 整个程序都可以使用
    private static SharedPreferences mSp;

    private GlobalManager() {

        if (mMap == null) {
            mMap = new HashMap<String, Object>();
        }
        if (mSet == null) {
            mSet = new HashSet<Activity>();
        }
        if (mSp == null) {
            mSp = App.getContent().getSharedPreferences("config", App.MODE_PRIVATE);
        }

    }

    public synchronized static GlobalManager getManager() {
        if (mManager == null) {
            synchronized (GlobalManager.class) {
                if (mManager == null) {
                    mManager = new GlobalManager();
                }
            }
        }
        return mManager;
    }

    public void save_str(String key, String value) {
        mSp.edit().putString(key, value);
    }
    public String get_str(String key) {
        String value = mSp.getString(key, "");
        return value;
    }

    public void save_boolean(String key, Boolean value) {
        mSp.edit().putBoolean(key, value);
    }
    public Boolean get_boolean(String key) {
        Boolean value = mSp.getBoolean(key, false);
        return value;
    }

    public void addActivity(Activity activity) {
        mSet.add(activity);
    }

    public void clearActivity() {
        for (Activity activity : mSet) {
            if (activity != null) {
                activity.finish();
                System.exit(0);//退出程序
            }
        }
    }

    public void getObj(String key, Object obj) {
        mMap.put(key, obj);
    }

    public Object getObj(String key) {
        Object o = mMap.get(key);
        return o;
    }
}
