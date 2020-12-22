package com.example.ceshi_06.mvp.mvp.model;


import com.example.ceshi_06.mvp.callback.RxObserverCallBack;
import com.example.ceshi_06.mvp.di.component.DaggerOkComponent;
import com.example.ceshi_06.mvp.mvp.model.api.ApiService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * 封装的网络请求操作类
 */
public class RxOpretorImpl {
    @Inject
    ApiService mApiService;

    public RxOpretorImpl() {
        DaggerOkComponent.create().
                getSingleApiService(this);
    }


    //没有参数也没有请求头的get请求
    public <T> void rxGetRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadtransformer(mApiService.requestGetData(url)).
                subscribe(new RxObserver(callBack));
    }


    //有参数没有请求头的get请求
    public <T> void rxGetRequest(String url, Map<String, T> params,
                                 RxObserverCallBack<T> callBack) {
        if (params != null && params.size() > 0) { //有参数的请求
            RxOperator.threadtransformer(mApiService.requestGetData(url, params)).
                    subscribe(new RxObserver(callBack));
        } else {  //没有参数的请求
            rxGetRequest(url, callBack);
        }
    }


    //有请求头但是没有参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers,
                                 RxObserverCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) {
            RxOperator.threadtransformer(mApiService.requestGetData(url, headers)).
                    subscribe(new RxObserver(callBack));
        } else {
            rxGetRequest(url, callBack);
        }
    }


    //既有请求头也有请求参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers,
                                 Map<String, T> params, RxObserverCallBack<T> callBack) {
        //既有请求头也有请求参数的get请求
        if (headers != null && headers.size() > 0 && params != null && params.size() > 0) {
            RxOperator.threadtransformer(mApiService.requestGetData(url, headers, params)).
                    subscribe(new RxObserver(callBack));
            //只有请求头没有请求参数的get请求
        } else if (headers != null && headers.size() > 0 && (params == null || params.size() == 0)) {
            rxGetRequest(url, headers, callBack);
            //只有参数但是没有请求头的get请求
        } else if (params != null && params.size() > 0 && (headers == null || headers.size() == 0)) {
            rxGetRequest(url, params, callBack);
        } else {
            //既没有请求头也没有请求参数的get请求
            rxGetRequest(url, callBack);
        }
    }




    //没有参数也没有请求头的post请求
    public <T> void rxPostRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadtransformer(mApiService.requestPostData(url)).
                subscribe(new RxObserver(callBack));
    }


}