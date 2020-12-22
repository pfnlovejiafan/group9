package com.example.p7mvp.threadFactory;

public class ThreadPoolFactory {
    public static final int CACHE_THREAD = 0;
    public static final int FIXED_THREAD = 1;
    public static final int SCHEDULED_THREAD = 2;
    public static final int SINGLE_THREAD = 3;

    public static ThreadFactoryInterface  getThreadPool(int type){
        switch (type){
            case 0:
                return new CacheThreadPool();
            case 1:
                return new FixedThreadPool();
            case 2:
                return new ScheduledThreadPool();
            case 3:
                return new SingleThreadPool();
        }
        return null;
    }
}
