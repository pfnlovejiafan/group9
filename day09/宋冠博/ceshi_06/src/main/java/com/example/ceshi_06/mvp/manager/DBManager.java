package com.example.ceshi_06.mvp.manager;

public class DBManager {
    private volatile static DBManager mManger;

    private DBManager() {

    }

    public static synchronized DBManager getmManger() {
        if (mManger == null) {
            synchronized (DBManager.class) {
                if (mManger == null) {
                    mManger = new DBManager();
                }
            }
        }
        return mManger;
    }

}