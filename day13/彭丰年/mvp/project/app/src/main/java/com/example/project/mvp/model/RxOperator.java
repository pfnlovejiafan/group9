package com.example.project.mvp.model;

import android.widget.Button;
import android.widget.CheckBox;


import com.example.project.callback.RxObserverCallBack;

import java.util.Timer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 用来封装各种操作符
 */
public class RxOperator {
    //封装子线程切换
    public static Observable threadTransFormer(Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //封装了ConcatMap操作符  网络嵌套(注册完之后自动登录)
    public <T> ObservableSource concatMap(Observable<T> registerObservable, Observable<T> loginObservable, RxObserverCallBack callBack) {

        return threadTransFormer(registerObservable).doOnNext(obj -> {
            callBack.onSucceed(obj);
        }).observeOn(Schedulers.io())
                .concatMap(obj -> {
                    return loginObservable;
                });

//       return threadTransFormer(registerObservable).doOnNext(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                callBack.onSucceed(o);
//            }
//        }).observeOn(Schedulers.io())
//                .concatMap(new Function() {
//                    @Override
//                    public Object apply(Object o) throws Exception {
//                        return loginObservable;
//                    }
//                });

        //lambda表达式
//        Button button = new Button(App.getContent());
//        button.setOnClickListener(v -> {
//        });


    }

}
