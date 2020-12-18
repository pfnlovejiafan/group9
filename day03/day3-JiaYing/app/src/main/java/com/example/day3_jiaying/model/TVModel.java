package com.example.day3_jiaying.model;

import com.example.day3_jiaying.ApiService;
import com.example.day3_jiaying.bean.PicInfo;
import com.example.day3_jiaying.callback.Resultback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class TVModel {
    public void requestmodel(final Resultback resultback){

        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTV("12","video")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PicInfo picInfo) {
                        if(resultback!=null)
                            resultback.showokData(picInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(resultback!=null)
                            resultback.shownoData(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
