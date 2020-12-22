package com.example.day6_one.manager;

/**
 * 数据库管理器
 * 可写数据库相关的代码
 */
public class DBManager {
    private volatile static  DBManager mManager;
    //提供无参构造

    public DBManager() {
    }
    public static synchronized DBManager getmManager(){
        if(mManager==null){
            synchronized (DBManager.class){
                if(mManager==null){
                    mManager=new DBManager();
                }
            }
        }
        return mManager;
    }
}
