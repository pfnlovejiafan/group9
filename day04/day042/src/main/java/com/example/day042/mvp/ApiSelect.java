package com.example.day042.mvp;

import com.example.day042.bean.JsonBean;
import com.example.day042.bean.PaoBean;
<<<<<<< HEAD
import com.example.day042.bean.SheBean;
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiSelect {
    public static final String BUAT_URL="http://cdwan.cn:7000/";

    @GET("tongpao/discover/hot_activity.json")
    Observable<JsonBean> getData();

    @GET("tongpao/discover/robe.json")
    Observable<PaoBean> getPao();
<<<<<<< HEAD
    @GET("tongpao/discover/association.json")
    Observable<SheBean> getShe();
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
}
