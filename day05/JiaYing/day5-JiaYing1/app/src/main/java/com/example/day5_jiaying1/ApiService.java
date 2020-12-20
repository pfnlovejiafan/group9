package com.example.day5_jiaying1;

import com.example.day5_jiaying1.bean.BannerInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * https://www.wanandroid.com/banner/json?cid
 */
public interface ApiService {
    public static final String BASE_URL="https://www.wanandroid.com/";

    @GET("banner/json")
    Observable<BannerInfo> getBanner();

}
