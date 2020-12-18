package com.example.skp7day04zuoye.model;

import com.example.skp7day04zuoye.model.bean.HuodongBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {
    ApiService apiService = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService.class);

    public void getHuodong(CallBack callBack){
        apiService.getHuodong().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HuodongBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(HuodongBean value) {
                callBack.getTrue(value);
            }
            @Override
            public void onError(Throwable e) {
                callBack.getFalse(e.getMessage());
            }
            @Override
            public void onComplete() {
            }
        });
    }
}
