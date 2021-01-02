package com.example.project.mvp.model;


import com.example.project.callback.RxObserverCallBack;
import com.example.project.di.component.DaggerOkComponent;
import com.example.project.mvp.model.api.ApiService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * 网络请求操作类
 */

public class RxPoretorImpl {
    @Inject
    ApiService mApiService;

    public RxPoretorImpl() {
        DaggerOkComponent.create()
                .getSingleApiService(this);
    }

    //没有参数也没有请求头的get请求
    public <T> void rxGetRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadTransFormer(mApiService.requestGetData(url))
                .subscribe(new RxObserver(callBack));
    }

    //有参数没有请求头的get请求
    public <T> void rxGetRequest(String url, Map<String, T> params, RxObserverCallBack<T> callBack) {
        if (params != null && params.size() > 0) {  //有参数的请求
            RxOperator.threadTransFormer(mApiService.requestGetData(url, params))
                    .subscribe(new RxObserver(callBack));
        } else {  //没有参数的请求
            rxGetRequest(url, callBack);
        }
    }

    //没有参数有请求头的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers, RxObserverCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) { //没有参数有请求头
            RxOperator.threadTransFormer(mApiService.requestGetData(url, headers))
                    .subscribe(new RxObserver(callBack));
        } else { //没参没请求头
            rxGetRequest(url, callBack);
        }
    }

    //有参数有请求头的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers, Map<String, T> params, RxObserverCallBack<T> callBack) {
        if (headers != null && headers.size() > 0 && params != null && params.size() > 0) {
            //既有请求头，也有参数的get请求
            RxOperator.threadTransFormer(mApiService.requestGetData(url, headers, params))
                    .subscribe(new RxObserver(callBack));
        } else if (headers != null && headers.size() > 0 && (params == null || params.size() == 0)) {
            //只有请求头，没有参数的get请求
            rxGetRequest(url, headers, callBack);
        } else if (params != null && params.size() > 0 && (headers == null || headers.size() == 0)) {
            //只有参数，没有请求头的get请求
            rxGetRequest(url, params, callBack);
        } else {
            //没有请求头也没有参数的get请求
            rxGetRequest(url, callBack);
        }

    }

    //没有参数也没有请求头的post请求
    public <T> void rxPostRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadTransFormer(mApiService.requestPostData(url))
                .subscribe(new RxObserver(callBack));
    }

    //没有参数也有请求头的post请求
    public <T> void rxPostRequest(String url, HashMap<String, T> headers, RxObserverCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) {    //没有参数也有请求头的post请求
            RxOperator.threadTransFormer(mApiService.requestPostData(url, headers))
                    .subscribe(new RxObserver(callBack));
        } else {//没有参数也有没求头的post请求
            rxPostRequest(url, callBack);
        }
    }
}
