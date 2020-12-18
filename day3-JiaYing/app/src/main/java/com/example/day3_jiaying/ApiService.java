package com.example.day3_jiaying;

import com.example.day3_jiaying.bean.PicInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=pics  沙发—图片
 http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=video 沙发—视频
 http://123.56.232.18:8080/serverdemo/feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275沙发— 文本
 http://123.56.232.18:8080/serverdemo//tag/queryTagList          发现—推荐

 */
public interface ApiService {
    public static final String BASE_URL="http://123.56.232.18:8080/";

    @GET("serverdemo/feeds/queryHotFeedsList")
    Observable<PicInfo> getPic(@Query("pageCount") String pageCount,
                               @Query("feedType") String feedType);

    @GET("serverdemo/feeds/queryHotFeedsList")
    Observable<PicInfo> getTV(@Query("pageCount") String page,
                               @Query("feedType") String feed);
}
