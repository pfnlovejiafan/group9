package com.example.skp7day03zuoye.model;

import com.example.skp7day03zuoye.model.bean.ImagesBean;
import com.example.skp7day03zuoye.model.bean.TextBean;
import com.example.skp7day03zuoye.model.bean.VideoBean;

import org.w3c.dom.Text;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    public static String BASE_URL = "http://123.56.232.18:8080/";
//    http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=pics  沙发—图片
//    http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=video 沙发—视频
//    http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275沙发— 文本
//    http://123.56.232.18:8080/serverdemo//tag/queryTagList          发现—推荐

    @GET("serverdemo/feeds/queryHotFeedsList")
    Observable<ImagesBean> getShaFaData(
            @Query("pageCount") Object pageCount,
            @Query("feedType") Object feedType);

    @GET("serverdemo/feeds/queryHotFeedsList")
    Observable<VideoBean> getShaFaData(
            @Query("pageCount") Object pageCount,
            @Query("feedType") Object feedType,
            @Query("feedId") Object feedId);

    @GET("/tag/queryTagList")
    Observable<TextBean> getFaXianData();

}
