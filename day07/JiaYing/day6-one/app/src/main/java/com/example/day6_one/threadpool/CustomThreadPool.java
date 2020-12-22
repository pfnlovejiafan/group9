package com.example.day6_one.threadpool;

import com.example.day6_one.base.BaseThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 对外提供自定义线程池对象  进行耗时 封装成单例
 * 一般用于处理耗时操作
 */
public class CustomThreadPool extends BaseThreadPool {

    private static  CustomThreadPool mThreadPool;
    private  ThreadPoolExecutor mExecutor;

    private CustomThreadPool(){
         mExecutor = new ThreadPoolExecutor(5, 30, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory());
    }


    public synchronized static CustomThreadPool  getThreadPool(){
        if(mThreadPool==null){
            synchronized (CustomThreadPool.class){
                if(mThreadPool==null){
                    mThreadPool =new CustomThreadPool();
                }
            }
        }
        return  mThreadPool;
    }


    //重写一个执行任务的方法
    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if(mExecutor!=null)
            mExecutor.execute(runnable);
    }

    @Override
    public void removeTask() {

    }

    //重写移除任务的方法

    @Override
    public void removeTask(Runnable runnable) {
        super.removeTask(runnable);
        if(mExecutor!=null)
            mExecutor.remove(runnable);
    }
}
