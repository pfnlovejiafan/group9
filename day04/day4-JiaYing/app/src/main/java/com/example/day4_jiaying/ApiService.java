package com.example.day4_jiaying;

import com.example.day4_jiaying.bean.PaoInfo;
import com.example.day4_jiaying.bean.SheInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * http://cdwan.cn:7000/tongpao/discover/association.json
 */
public interface ApiService {
    public static final String BASE_URL="http://cdwan.cn:7000/tongpao/";

    @GET("discover/hot_activity.json")
    Observable<PaoInfo> getPao();

    @GET("discover/association.json")
    Observable<SheInfo> getShe();
}
