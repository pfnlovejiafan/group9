package com.example.p7mvp.threadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThreadPool implements ThreadFactoryInterface{

    private ScheduledExecutorService scheduledExecutor;

    @Override
    public void createThread(Runnable runnable) {
        if (scheduledExecutor != null)
            return;
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(runnable,2, TimeUnit.DAYS);
    }

    @Override
    public void removeThread() {
        if (scheduledExecutor != null)
            scheduledExecutor.shutdown();
    }
}
