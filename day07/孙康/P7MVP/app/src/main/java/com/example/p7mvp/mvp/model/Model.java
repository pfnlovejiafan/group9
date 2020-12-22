package com.example.p7mvp.mvp.model;


import com.example.p7mvp.callBcak.RxCallBack;
import com.example.p7mvp.di.DaggerRxExecutorComponent;
import com.example.p7mvp.manager.ContainManager;
import com.example.p7mvp.mvp.model.rxjava.RxExecutor;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class Model {

    @Inject
    public RxExecutor executor;

    public Model() {
        DaggerRxExecutorComponent.create().getExecutor(this);
    }

    public<T> void getData(String url, RxCallBack<T> callBack){
        executor.resultGet(url, new RxCallBack<T>() {
            @Override
            public void getDisposable(Disposable disposable) {
                callBack.getDisposable(disposable);
            }

            @Override
            public void resultSucceed(T t) {
                callBack.resultSucceed(t);
            }

            @Override
            public void error(Throwable error) {
                callBack.error(error);
            }
       });
    }
}
