package com.example.project.mvp.model;

import android.util.Log;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.callback.RxObserverCallBack;
import com.google.gson.JsonParseException;

import java.io.IOException;

import javax.net.ssl.SSLException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer {
    private static final String TAG = RxObserver.class.getSimpleName();

    private RxObserverCallBack<T> callBack;

    public RxObserver(RxObserverCallBack<T> callBack) {

        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (callBack != null) {
            callBack.onSubscribe(d);
        }
    }

    @Override
    public void onNext(Object o) {
        if (callBack != null) {
            callBack.onSucceed((T) o);
        }
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof IOException) {
//            Log.e(TAG, "onError: " + "网络连接异常");
            Log.e(TAG, "onError: " + App.getStr(R.string.connect_exception));
        } else if (e instanceof JsonParseException) {
//            Log.e(TAG, "onError: " + "json解析异常");
            Log.e(TAG, "onError: " + App.getStr(R.string.jsonParse_exception));
        } else if (e instanceof SSLException) {
//            Log.e(TAG, "onError: " + "ssl证书异常");
            Log.e(TAG, "onError: " + App.getStr(R.string.ssl_exception));
        }
        if (callBack != null) {
            callBack.onFiled(e.getMessage());
        }

    }


    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: " + App.getStr(R.string.complete));
    }
}
