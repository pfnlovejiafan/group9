package com.example.demo_03.model;

import com.example.demo_03.bean.ReMenBean;
import com.example.demo_03.callback.Homecallback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel {
    public void request(final Homecallback homecallback) {
        new Retrofit.Builder()
                .baseUrl(Apiservice.a)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Apiservice.class)
                .getReMen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReMenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReMenBean value) {
                        homecallback.OnChenggong(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        homecallback.OnShibai(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
