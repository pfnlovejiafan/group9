package com.example.day042.mvp;

import com.example.day042.bean.JsonBean;
import com.example.day042.bean.PaoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiSelect {
    public static final String BUAT_URL="http://cdwan.cn:7000/";

    @GET("tongpao/discover/hot_activity.json")
    Observable<JsonBean> getData();

    @GET("tongpao/discover/robe.json")
    Observable<PaoBean> getPao();
}
