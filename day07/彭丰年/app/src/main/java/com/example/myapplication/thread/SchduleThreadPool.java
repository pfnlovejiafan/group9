package com.example.myapplication.thread;

import com.example.myapplication.bean.BaseThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchduleThreadPool extends BaseThreadPool {
    private static SchduleThreadPool mSchduleThreadPool;
    private ScheduledExecutorService mExecuteService;
    @Override
    public void executeTimerTask(Runnable runnable, long delayTime, TimeUnit timeUnit) {
        mExecuteService.schedule(runnable,delayTime,timeUnit);
    }

    @Override
    public void executeTimerTask(Runnable runnable, long firstarTime, long intervelTime, TimeUnit timeUnit) {
        mExecuteService.scheduleWithFixedDelay(runnable, firstarTime, intervelTime, timeUnit);
    }

    @Override
    public void removeTask() {

    }

    public SchduleThreadPool() {
        mExecuteService= Executors.newSingleThreadScheduledExecutor();
    }
    public synchronized static SchduleThreadPool getmSchduleThreadPool(){
        if (mSchduleThreadPool==null){
            synchronized (SchduleThreadPool.class){
                mSchduleThreadPool=new SchduleThreadPool();
            }
        }
        return mSchduleThreadPool;
    }
}
