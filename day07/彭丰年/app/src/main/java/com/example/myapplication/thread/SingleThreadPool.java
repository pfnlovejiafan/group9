package com.example.myapplication.thread;

import com.example.myapplication.bean.BaseThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool extends BaseThreadPool {
    private static SingleThreadPool mSchduleThreadPool;
    private ExecutorService mExecutorService;

    @Override
    public void removeTask() {

    }

    public SingleThreadPool() {
        mExecutorService= Executors.newSingleThreadScheduledExecutor();
    }
    public static synchronized SingleThreadPool getmSchduleThreadPool(){
        if (mSchduleThreadPool==null){
            synchronized (SingleThreadPool.class){
                if (mSchduleThreadPool==null){
                    mSchduleThreadPool=new SingleThreadPool();
                }
            }
        }
        return mSchduleThreadPool;
    }

    @Override
    public void executeTask(Runnable runnable) {
        if (mExecutorService!=null){
            mExecutorService.execute(runnable);
        }
    }

    @Override
    public void removeTask(Runnable runnable) {
        super.removeTask(runnable);
        mExecutorService.shutdown();
    }
}
