package com.example.day6_one.base;

import android.app.Application;
import android.content.Context;


//此方法：只存放上下文即可
//传对象、sp、退出程序 新建一个类调用即可  这样不会占用内存、造成程序卡顿
/**
 * 用于存放单例对象：例如传对象、sp、退出程序
 * 泛型的作用：保证数据的安全性 起到限制作用
 * sp:数据持久化
 *
 * 这样做的好处是：可以直接类名调用
 * 缺点是：程序每次执行都是先执行清单文件、然后接着执行这个方法
 * 代码从上往下执行  先执行app
 */
public class App extends Application {

    //上下文 不管activity还是Fragment都需要  每个程序都可以调用
    private static Context mContext;


    //重写onCreate方法
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContext=this;
    }

    //提供了上下文的静态方法
    public static  Context context(){
        return mContext;
    }

}
