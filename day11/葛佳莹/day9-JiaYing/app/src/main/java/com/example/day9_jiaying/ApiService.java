package com.example.day9_jiaying;

import com.example.day9_jiaying.bean.BannerInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    public static final String BASE_URL="https://www.wanandroid.com/";

    @GET("banner/json")
    Observable<BannerInfo> getBanner();

}