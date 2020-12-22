package com.example.mvp.mvp.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.mvp.mvp.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

public class ContainManger {
    private volatile static ContainManger mManager;
    private Map<String ,Object> mMap;
    private Set<Activity> mSet;
    private SharedPreferences mSp;
    private ContainManger(){
        if (mMap==null){
            mMap=new HashMap<String ,Object>();
        }
        if (mSet==null){
            mSet=new HashSet<Activity>();
        }
        if (mSp==null){
            mSp= App.context().getSharedPreferences("config",App.MODE_APPEND);
        }
    }
    public void save_str(String key,String value){
        mSp.edit().putString(key,value).commit();
    }
    public String get_str(String key){
        return mSp.getString(key,"");
    }
    public void save_boolean(String key,Boolean value){
        mSp.edit().putBoolean(key,value).commit();
    }
    public boolean get_boolean(String key){
        return mSp.getBoolean(key,false);
    }
    public void addActivity(Activity activity){
        mSet.add(activity);
    }
    public void putobj(String key, Observer obj){
        mMap.put(key,obj);
    }
    public Object getobj(String key){
        Object object=mMap.get(key);
        return object;
    }
    public static synchronized ContainManger getmManager(){
        if (mManager==null){
            synchronized (ContainManger.class){
                if (mManager==null){
                    mManager=new ContainManger();
                }
            }
        }
        return mManager;
    }

}
