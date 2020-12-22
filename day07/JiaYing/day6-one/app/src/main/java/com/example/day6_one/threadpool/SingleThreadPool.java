package com.example.day6_one.threadpool;

import com.example.day6_one.base.BaseThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程线程池：用于子线程、直接用单线程就可以了
 */
public class SingleThreadPool extends BaseThreadPool {
    private static SingleThreadPool mSchduleThreadPool;
    private  ExecutorService mExecutorService;

    private SingleThreadPool(){
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized SingleThreadPool getmSchduleThreadPool(){
        if(mSchduleThreadPool==null){
            synchronized (SingleThreadPool.class){
                if(mSchduleThreadPool==null){
                    mSchduleThreadPool=new SingleThreadPool();
                }
            }
        }
        return mSchduleThreadPool;
    }


    //重写一个执行任务的方法
    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if(mExecutorService!=null)
            mExecutorService.execute(runnable);
    }

    @Override
    public void removeTask() {

    }
}
