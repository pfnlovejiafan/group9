package com.example.mvp.mvp.model;

import com.example.mvp.base.ApiServer;
import com.example.mvp.bean.HuoBean;
import com.example.mvp.callback.RxCallBack;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel {
    //3.M层请求到数据之后，通过接口回调将数据回传给P层
    public void requestData(RxCallBack callBack) {
//        Request request = new Request.Builder().
//                url("https://fanyi.baidu.com/?aldtype=85#en/zh/detach").build();
//        new OkHttpClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                callBack.onErrorMsg(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String string = response.body().string();
//                callBack.onSuccessData(string);
//            }
//        });
       new Retrofit.Builder ()
               .baseUrl ( ApiServer.str )
               .addConverterFactory ( GsonConverterFactory.create () )
               .addCallAdapterFactory ( RxJava2CallAdapterFactory.create () )
               .build ()
               .create ( ApiServer.class )
               .getData ()
               .subscribeOn ( Schedulers.io () )
               .observeOn ( AndroidSchedulers.mainThread () )
               .subscribe ( new Observer<HuoBean> () {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(HuoBean value) {
                       List<HuoBean.DataBean> data = value.getData ();
                       if (callBack!=null){
                           callBack.onSuccessData ( data );
                       }

                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               } );


    }
}