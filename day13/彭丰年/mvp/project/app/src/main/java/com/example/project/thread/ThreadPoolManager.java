package com.example.project.thread;


import com.example.project.mvp.model.constants.Constants;

public class ThreadPoolManager {

    public static BaseThreadPool getThreadPool(int type) {
        switch (type) {
            case Constants.CUSTOM_THREADPOOL:
                return CustomThreadPool.getThreadPool();
            case Constants.SINGLE_THREADPOOL:
                return SingleThreadPool.getThreadPool();
            case Constants.SCHEDULED_THREADPOOL:
                return ScheduledThreadPool.getThreadPool();
        }
        return null;
    }
}
