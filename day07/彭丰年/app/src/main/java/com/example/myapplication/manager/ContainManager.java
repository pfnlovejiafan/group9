package com.example.myapplication.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.myapplication.bean.App;

import java.util.HashMap;
import java.util.HashSet;

public class ContainManager {
    private volatile static ContainManager containManager;
    private  SharedPreferences mSp;
    private HashSet<Activity> set;
    private HashMap<String, Object> map;

    public static synchronized ContainManager getContainManager() {
        if (containManager == null) {
            synchronized (ContainManager.class) {
                if (containManager == null) {
                    containManager = new ContainManager();
                }
            }
        }
        return containManager;
    }


    private ContainManager() {
        if (map == null) {
            map = new HashMap<>();
        }
        if (set == null) {
            set = new HashSet<>();
        }
        if (map == null) {
            mSp = App.context().getSharedPreferences("flay", App.MODE_PRIVATE);
        }
    }

    public void save_str(String str, String object) {
        mSp.edit().putString(str, object).commit();
    }

    public String get_str(String key) {
        return mSp.getString(key, "");
    }

    public void save_boo(String key, Boolean values) {
        mSp.edit().putBoolean(key, values).commit();
    }

    public Boolean getBoolues(String key) {
        return mSp.getBoolean(key, false);
    }

    public void addActivity(Activity activity) {
        set.add(activity);
    }

    public void clearActivity() {
        for (Activity activity : set) {
            if (activity!=null){
                activity.finish();
                System.exit(0);
            }
        }
    }
    public void pouObj(String key,Object object){
        map.put(key,object);
    }
    public Object getObj(String key){
        Object o = map.get(key);
        return o;
    }
}
