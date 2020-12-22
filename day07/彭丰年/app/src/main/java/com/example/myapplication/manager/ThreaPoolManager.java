package com.example.myapplication.manager;

import com.example.myapplication.bean.BaseThreadPool;
import com.example.myapplication.thread.CustomThreadPool;
import com.example.myapplication.thread.SchduleThreadPool;
import com.example.myapplication.thread.SingleThreadPool;

public class ThreaPoolManager {
    public static final int CUSTOM_THREADPOOL=0;
    public static final int SINGLE_THREADPOOL=1;
    public static final int SCHDULE_THREADPOOL=2;
    public static BaseThreadPool getThreadPool(int type){
        switch (type){
            case CUSTOM_THREADPOOL:
                return CustomThreadPool.getInstance();
            case SINGLE_THREADPOOL:
                return SingleThreadPool.getmSchduleThreadPool();
            case SCHDULE_THREADPOOL:
                return SchduleThreadPool.getmSchduleThreadPool();
        }
        return null;
    }
}
