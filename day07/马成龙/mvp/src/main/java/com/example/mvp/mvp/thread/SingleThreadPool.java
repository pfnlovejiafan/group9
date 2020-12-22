package com.example.mvp.mvp.thread;

import com.example.mvp.mvp.base.BaseThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool extends BaseThreadPool {

    private static SingleThreadPool mSchduleThreadPool;
    private ExecutorService mExecutorService;

    private SingleThreadPool() {
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized SingleThreadPool getSingleThreaPool() {
        if (mSchduleThreadPool == null) {
            synchronized (SingleThreadPool.class) {
                if (mSchduleThreadPool == null) {
                    mSchduleThreadPool = new SingleThreadPool();
                }
            }
        }
        return mSchduleThreadPool;
    }


    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if (mExecutorService != null)
            mExecutorService.execute(runnable);
    }

    @Override
    public void removeTask() {
        mExecutorService.shutdown();
    }
}