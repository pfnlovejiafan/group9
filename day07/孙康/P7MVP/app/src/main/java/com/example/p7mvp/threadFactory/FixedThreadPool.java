package com.example.p7mvp.threadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadFactoryInterface {


    private ExecutorService fixedThreadPool;

    @Override
    public void createThread(Runnable runnable) {
        if (fixedThreadPool != null)
            return;
        fixedThreadPool = Executors.newFixedThreadPool(10);
        fixedThreadPool.execute(runnable);
    }

    @Override
    public void removeThread() {
        if (fixedThreadPool != null)
            fixedThreadPool.shutdown();
    }
}
