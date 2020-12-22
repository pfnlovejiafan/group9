package com.example.p7mvp.mvp.model.rxjava;

import com.example.p7mvp.callBcak.RxCallBack;
import com.example.p7mvp.di.DaggerRxComponent;
import com.example.p7mvp.mvp.model.api.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
@Module
public class RxExecutor {

    @Inject
    public ApiService apiService;

    public RxExecutor() {
        DaggerRxComponent.create().getSingleApiService(this);
    }

    @Provides
    @Singleton
    public RxExecutor getExecutor(){
        return RxExecutor.this;
    }

    //无参无头get请求
    public<T> void resultGet(String url , RxCallBack<T> rxCallBack){
        Observable operators =
                RxOperators.operators(apiService.requestGet(url));
        operators.subscribe(new RxObserver(rxCallBack));
    }
    //无参无头post请求
    public<T> void resultPost(String url , RxCallBack<T> rxCallBack){
        Observable operators =
                RxOperators.operators(apiService.requestPost(url));
        operators.subscribe(new RxObserver(rxCallBack));
    }






}
