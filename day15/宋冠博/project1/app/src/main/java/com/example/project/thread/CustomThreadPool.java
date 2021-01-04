package com.example.project.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool extends BaseThreadPool {
    private static CustomThreadPool mThreadPool;
    private ThreadPoolExecutor mExecutor;

    private CustomThreadPool() {
        mExecutor = new ThreadPoolExecutor(5, 20, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());
    }

    public synchronized static CustomThreadPool getThreadPool() {
        if (mThreadPool == null) {
            synchronized (CustomThreadPool.class) {
                if (mThreadPool == null) {
                    mThreadPool = new CustomThreadPool();
                }
            }
        }
        return mThreadPool;
    }

    @Override
    public void executorTask(Runnable runnable) {
        if (mExecutor == null)
            return;
        mExecutor.execute(runnable);
    }

    @Override
    public void removeTask(Runnable runnable) {
        if (mExecutor == null)
            return;
        mExecutor.remove(runnable);
    }
}
