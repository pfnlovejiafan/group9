package com.example.day6_one.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.day6_one.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainManager {
    //volatile 保证线程安全 只有一个mManager对象
    private volatile static  ContainManager mManager;
    //用于两个类之间传对象
    private  Map<String,Object> mMap;
    //定义一个集合存储所有需要添加到集合的activity
    private  Set<Activity> mSet;
    //sp的使用
    private  SharedPreferences mSp;



    //提供无参构造
    public ContainManager() {

        if(mMap==null){
            mMap=new HashMap<String,Object>();
        }

        if(mSet==null){
            mSet=new HashSet<Activity>();
        }

        if(mSp==null){
            mSp= App.context().getSharedPreferences("config",App.MODE_PRIVATE);
        }
    }

    //提供了sp的静态方法
    public  void save_str(String key,String value){
        mSp.edit().putString(key,value).commit();
    }

    public  String get_str(String key){
        //通过key获取values值的时候、第二个参数默认为空字符串
        //并将它return返回
        return mSp.getString(key,"");
    }


    //布尔类型
    public void save_boolean(String key,Boolean value){
        mSp.edit().putBoolean(key,value).commit();
    }
    public boolean get_boolean(String key){
        return mSp.getBoolean(key,false);
    }


    //提供了程序退出的put/get方法
    public   void  addActivity(Activity activity){
            mSet.add(activity);
    }

    public  void  clearActivity(){

            //对mSet增强for循环
            for (Activity activity : mSet) {
                if(activity!=null){
                    activity.finish();
                    System.exit(0);
                }
        }
    }



    //提供传对象的put/get方法
    public   void putObj(String key,Object value){
            mMap.put(key,value);

    }
    public  Object  getObj(String key){
        //获取值  只需要键就可以获得
            Object Object = mMap.get(key);
            return Object;
    }

    //设置同步锁
    public  static  synchronized ContainManager getmManager(){
       if(mManager==null){
           synchronized (ContainManager.class){
               if(mManager==null){
                   mManager=new ContainManager();
               }
           }
       }
       return mManager;
    }
}
