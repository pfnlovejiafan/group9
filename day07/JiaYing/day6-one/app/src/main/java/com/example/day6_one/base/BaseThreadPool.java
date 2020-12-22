package com.example.day6_one.base;

import java.util.concurrent.TimeUnit;

/**
 * 多种线程池的基类  抽象类  里面的方法：线程池需要重写
 */
public abstract  class BaseThreadPool {

    //执行任务的方法
    public void executeTask(Runnable runnable){

    }


    //开始时间   间隔时间
    public void  executeTimerTask(Runnable runnable,
                                  long firstStartTime,
                                  long intervalTime,
                                  TimeUnit timeUnit
                                  ){

    }

    //方法的重载：同名参数的列表是方法重载
    public void  executeTimerTask(Runnable runnable,
                                  long delayTime,
                                  TimeUnit timeUnit
    ){}


    //移除任务的方法
    public abstract void removeTask();

    public void removeTask(Runnable runnable) {

    }
}
