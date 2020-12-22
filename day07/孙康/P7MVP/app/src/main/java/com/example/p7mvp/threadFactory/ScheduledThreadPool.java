package com.example.p7mvp.threadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledThreadPool implements ThreadFactoryInterface {


    private ScheduledExecutorService scheduledThreadPool;

    @Override
    public void createThread(Runnable runnable) {
        if (scheduledThreadPool != null)
            return;
        scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.execute(runnable);
    }

    @Override
    public void removeThread() {
        if (scheduledThreadPool != null)
            scheduledThreadPool.shutdown();
    }
}
