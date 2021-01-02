package com.example.project.thread;

import java.util.concurrent.TimeUnit;

public abstract class BaseThreadPool {
    public void executorTask(Runnable runnable) {
    }

    public void executorTimerTask(Runnable runnable, long firstStart, long interval, TimeUnit unit) {
    }

    public void executorTimerTask(Runnable runnable, long delayed,TimeUnit unit) {
    }


    public void removeTask(Runnable runnable) {
    }

    public void removeTask() {
    }
}
