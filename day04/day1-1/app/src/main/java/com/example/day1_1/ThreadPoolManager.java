package com.example.day1_1;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 */
public class ThreadPoolManager {
    //设置懒汉式的写法
    private ThreadPoolExecutor mExecutor;
    private  static  ThreadPoolManager mManager;

    //并提供无参构造方法
    private ThreadPoolManager(){
        mExecutor=new ThreadPoolExecutor(5,20,
                30,TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>()
        ,Executors.defaultThreadFactory());
    }


    //进行双重判断
    public synchronized  static ThreadPoolManager getmManager(){
        if(mManager==null){
            synchronized (ThreadPoolManager.class){
                if(mManager==null)
                    //创建对象
                    mManager=new ThreadPoolManager();
            }
        }
        //最后将对象给返回
        return  mManager;
    }


    //这是执行任务的方法
    public void executeTask(Runnable runnable){
        if(mExecutor==null)
            return;
        mExecutor.execute(runnable);
    }


    //这是移除任务的方法
    public  void removeTask(Runnable runnable){
        if(mExecutor==null)
            return;
        mExecutor.remove(runnable);
    }
}



