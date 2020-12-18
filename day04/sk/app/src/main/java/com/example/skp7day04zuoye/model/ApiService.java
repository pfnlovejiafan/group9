package com.example.skp7day04zuoye.model;

import com.example.skp7day04zuoye.model.bean.DengjiBean;
import com.example.skp7day04zuoye.model.bean.HuodongBean;
import com.example.skp7day04zuoye.model.bean.PaoziBean;
import com.example.skp7day04zuoye.model.bean.QiandaoBean;
import com.example.skp7day04zuoye.model.bean.ShetuanBean;
import com.example.skp7day04zuoye.model.bean.TuhaoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    public static String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    /*排行榜--等级榜  http://cdwan.cn:7000/tongpao/discover/rank_level.json
    排行榜--签到榜 http://cdwan.cn:7000/tongpao/discover/rank_sign.json
    排行榜--土豪榜 http://cdwan.cn:7000/tongpao/discover/rank_money.json
    袍子  /discover/robe.json
    热门活动：http://cdwan.cn:7000/tongpao/discover/hot_activity.json
    分类导航： http://cdwan.cn:7000/tongpao/discover/navigation.json
    如：热点  妆造 。。。。
    袍子---为你推荐  http://cdwan.cn:7000/tongpao/discover/navigation.json/discover/robe.json
    袍子---附近的袍子：http://cdwan.cn:7000/tongpao/discover/robe_near.json
    社团  ：http://cdwan.cn:7000/tongpao/discover/association.json
    */

    @GET("discover/robe.json")
    Observable<PaoziBean> getPaozi();

    @GET("discover/hot_activity.json")
    Observable<HuodongBean> getHuodong();

    @GET("discover/association.json")
    Observable<ShetuanBean> getShetuan();

    @GET("discover/rank_level.json")
    Observable<DengjiBean> getdengji();

    @GET("discover/rank_sign.json")
    Observable<QiandaoBean> getQiandao();

    @GET("discover/discover/rank_money.json")
    Observable<TuhaoBean> getTuhao();
}
