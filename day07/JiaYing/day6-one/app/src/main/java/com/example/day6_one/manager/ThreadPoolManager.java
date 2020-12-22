package com.example.day6_one.manager;

import com.example.day6_one.base.BaseThreadPool;
import com.example.day6_one.threadpool.CustomThreadPool;
import com.example.day6_one.threadpool.SchduleThreadPool;
import com.example.day6_one.threadpool.SingleThreadPool;

/**
 * 线程池的管理类
 */
public class ThreadPoolManager {

    public static final int CUSTOM_THREADPOOL = 0;
    public static final int SINGLE_THREADPOOL = 1;
    public static final int SCHDULE_THREADPOOL = 2;

    public static BaseThreadPool getThreadPool(int type){
        switch (type){
            case CUSTOM_THREADPOOL:
                return CustomThreadPool.getThreadPool();
            case SINGLE_THREADPOOL:
                return SingleThreadPool.getmSchduleThreadPool();
            case SCHDULE_THREADPOOL:
                return SchduleThreadPool.getmSchduleThreadPool();
        }
        return null;
    }
}
