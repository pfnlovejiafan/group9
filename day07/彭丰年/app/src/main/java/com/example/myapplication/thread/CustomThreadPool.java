package com.example.myapplication.thread;

import com.example.myapplication.bean.BaseThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool extends BaseThreadPool {
    private static CustomThreadPool customThreadPool;
    private final ThreadPoolExecutor executor;

    private CustomThreadPool(){
        executor = new ThreadPoolExecutor(5, 20, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
    }
    public synchronized static CustomThreadPool getInstance(){
        if (customThreadPool==null){
            synchronized (CustomThreadPool.class){
                if (customThreadPool==null){
                    customThreadPool=new CustomThreadPool();
                }
            }
        }
        return customThreadPool;
    }

    @Override
    public void removeTask() {

    }

    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if (executor!=null){
            executor.execute(runnable);
        }
    }

    @Override
    public void removeTask(Runnable runnable) {
        if (executor!=null){
            executor.remove(runnable);
        }
        super.removeTask(runnable);
    }
}
