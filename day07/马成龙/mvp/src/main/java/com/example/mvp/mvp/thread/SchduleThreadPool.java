package com.example.mvp.mvp.thread;


import com.example.mvp.mvp.base.BaseThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchduleThreadPool extends BaseThreadPool {
    private static SchduleThreadPool mSchduleThreadPool;
    private ScheduledExecutorService mExecuteService;

    @Override
    public void executeTimerTask(Runnable runnable, long firstStartTime,
                                 long intervelTime, TimeUnit timeUnit) {
        mExecuteService.scheduleWithFixedDelay(runnable, firstStartTime, intervelTime, timeUnit);
    }

    @Override
    public void executeTimerTask(Runnable runnable, long delayTime, TimeUnit timeUnit) {
        mExecuteService.schedule(runnable, delayTime, timeUnit);
    }

    private SchduleThreadPool() {
        mExecuteService = Executors.newSingleThreadScheduledExecutor();
    }


    public synchronized static SchduleThreadPool getmSchduleThreadPool() {
        if (mSchduleThreadPool == null) {
            synchronized (SchduleThreadPool.class) {
                if (mSchduleThreadPool == null) {
                    mSchduleThreadPool = new SchduleThreadPool();
                }
            }
        }
        return mSchduleThreadPool;
    }

    @Override
    public void removeTask() {

    }
}