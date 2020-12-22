package com.example.ceshi_06.mvp.base;

import java.util.concurrent.TimeUnit;

public abstract class BaseThreadPool {
    public void executeTask(Runnable runnable) {

    }

    public void executeTimerTask(Runnable runnable, long firstStartTime, long intervelTime,
                                 TimeUnit timeUnit) {
    }

    public void executeTimerTask(Runnable runnable, long delayTime
                                 ,TimeUnit timeUnit) {
    }

    public abstract void removeTask();

    public void removeTask(Runnable runnable) {

    }

}
