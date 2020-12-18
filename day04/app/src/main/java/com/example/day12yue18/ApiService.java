package com.example.day12yue18;



import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    public static String URL="http://cdwan.cn:7000/tongpao/";
    @GET("discover/robe.json")
    Observable<FaXianBean> lie();
}
