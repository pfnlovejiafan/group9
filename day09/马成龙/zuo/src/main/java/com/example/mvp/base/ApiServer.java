package com.example.mvp.base;

import com.example.mvp.bean.HuoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
    public static final String str="http://cdwan.cn:7000/";
    @GET("tongpao/discover/hot_activity.json")
    Observable<HuoBean> getData();
}
