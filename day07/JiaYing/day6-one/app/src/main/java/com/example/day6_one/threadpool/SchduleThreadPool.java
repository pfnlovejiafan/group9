package com.example.day6_one.threadpool;

import com.example.day6_one.base.BaseThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时线程池  可以操作定时任务
 * 关于时间的操作、倒计时等
 * 延迟多久、执行某个请求
 */
public class SchduleThreadPool extends BaseThreadPool {

    private static  SchduleThreadPool mSchduleThreadPool;
    private ScheduledExecutorService mExecuteService;

    private SchduleThreadPool(){
         mExecuteService = Executors.newSingleThreadScheduledExecutor();
    }

    //这是重写的方法
    @Override
    public void executeTimerTask(Runnable runnable, long firstStartTime, long intervalTime, TimeUnit timeUnit) {
        super.executeTimerTask(runnable, firstStartTime, intervalTime, timeUnit);
        mExecuteService.scheduleWithFixedDelay(runnable,firstStartTime,intervalTime,timeUnit);
    }

    //这是重载的方法
    @Override
    public void executeTimerTask(Runnable runnable, long delayTime, TimeUnit timeUnit) {
        mExecuteService.schedule(runnable,delayTime,timeUnit);
    }


    public synchronized static SchduleThreadPool getmSchduleThreadPool(){
        if(mSchduleThreadPool==null){
            synchronized (SchduleThreadPool.class){
                if(mSchduleThreadPool==null){
                    mSchduleThreadPool=new SchduleThreadPool();
                }
            }
        }
        return mSchduleThreadPool;
    }

    @Override
    public void removeTask() {

    }
}
