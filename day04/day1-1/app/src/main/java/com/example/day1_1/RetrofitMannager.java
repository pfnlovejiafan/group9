package com.example.day1_1;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 以单例的方式封装RetrofitMannager对象
 */
public class RetrofitMannager {
    private static  RetrofitMannager mManager;
    private ApiService mApiService;

    //提供无参构造方法
    private RetrofitMannager(){
        //可以添加拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mApiService=new Retrofit.Builder().
        baseUrl(ApiService.BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                client(builder.build()).build().
                create(ApiService.class);
    }

    //进行双重封锁
    public  synchronized  static  RetrofitMannager getmManager(){
        if(mManager==null)
            synchronized (RetrofitMannager.class){
            if(mManager==null){
                mManager=new RetrofitMannager();
            }
            }
        //最后将对象返回
        return mManager;
    }

    public ApiService getmApiService(){
        return mApiService;
    }
}
