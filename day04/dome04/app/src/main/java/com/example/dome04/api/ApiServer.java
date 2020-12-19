package com.example.dome04.api;

import com.example.dome04.bean.CommunityBean;
import com.example.dome04.bean.HotBean;
import com.example.dome04.bean.Hot_ActivityBean;
import com.example.dome04.bean.NavigationBean;
import com.example.dome04.bean.RodeBean;
import com.example.dome04.bean.RodeNearBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServer {
    String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    @GET("discover/hot_activity.json")
    Observable<Hot_ActivityBean> getHotActivityBean();

    @GET("discover/navigation.json")
    Observable<NavigationBean> getFoundNavigationBean();

    @GET("discover/news_{type}.json")
    Observable<HotBean> getHotBean(@Path("type") String type);

    @GET("discover/robe.json")
    Observable<RodeBean> getRodeBean();

    @GET("discover/robe_near.json")
    Observable<RodeNearBean> getRodeNearBean();

    @GET("discover/association.json")
    Observable<CommunityBean> getCommunityBean();
}
