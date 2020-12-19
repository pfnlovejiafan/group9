package com.example.demo_03.model;

import com.example.demo_03.bean.DataaBean;
import com.example.demo_03.bean.ReMenBean;
import com.example.demo_03.bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Apiservice {
    public String a="http://cdwan.cn:7000/tongpao/";

    @GET("discover/hot_activity.json")
    Observable<ReMenBean> getReMen();

    @GET("discover/navigation.json")
    Observable<TabBean> getTab();

    @GET("discover/news_{path}.json")
    Observable<DataaBean> getData(@Path("path")int path);
}
