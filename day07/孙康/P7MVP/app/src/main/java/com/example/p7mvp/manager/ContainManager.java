package com.example.p7mvp.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.p7mvp.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ContainManager {
    private static volatile ContainManager containManager;
    private Map<String, Object> mMap;
    private SharedPreferences mSP;
    private Set<Activity> mActivitySet;

    private ContainManager() {

        if (mMap == null)
            mMap = new HashMap<>();

        if (mActivitySet == null)
            mActivitySet = new HashSet<>();

        if (mSP == null)
            mSP = Objects.requireNonNull(App.getContext()).getSharedPreferences("user", App.MODE_PRIVATE);
    }

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

    public Object getMap_Object(String key){
       return mMap.get(key);
    }
    public void setMap_Object(String key,Object value){
        mMap.put(key,value);
    }

    public String getSP_String(String key){
       return mSP.getString(key,"");
    }
    public void setSP_String(String key,String value){
        mSP.edit().putString(key,value).apply();
    }

    public String getSP_Boolean(String key){
        return mSP.getString(key,"");
    }
    public void setSP_Boolean(String key,Boolean value){
        mSP.edit().putBoolean(key,value).apply();
    }

    public  void addActivity(Activity activity){
        mActivitySet.add(activity);
    }
    public void onExit(){
        mActivitySet.clear();
        System.exit(0);
    }

}
