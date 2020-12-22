package com.example.p7mvp.threadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPool implements ThreadFactoryInterface {

    private ExecutorService cachedThreadPool;

    @Override
    public void createThread(Runnable runnable) {
        if (cachedThreadPool != null)
            return;
        cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(runnable);
    }

    @Override
    public void removeThread() {
        if (cachedThreadPool != null)
            cachedThreadPool.shutdown();
    }
}
