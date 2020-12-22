package com.example.myapplication.manager;

public class DBManager {
    private volatile static DBManager mDbManager;
    public DBManager() {
    }
    public static synchronized DBManager getDbManager(){
        synchronized (DBManager.class){
            if (mDbManager!=null){
                mDbManager=new DBManager();
            }
        }
        return mDbManager;
    }
}
