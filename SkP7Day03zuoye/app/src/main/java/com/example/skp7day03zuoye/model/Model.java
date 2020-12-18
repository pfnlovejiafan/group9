package com.example.skp7day03zuoye.model;

import android.util.Log;

import com.example.skp7day03zuoye.model.ApiService;
import com.example.skp7day03zuoye.model.bean.ImagesBean;
import com.example.skp7day03zuoye.model.bean.RetrofitPackage;
import com.example.skp7day03zuoye.model.bean.TextBean;
import com.example.skp7day03zuoye.model.bean.VideoBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Model {
    ApiService apiService = RetrofitPackage.getRetrofitPackage().apiService;

    public void getShaFaData(int pageCount,String feedType,CallBack callBack){
        apiService.getShaFaData(pageCount,feedType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImagesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImagesBean value) {
                        callBack.getTrue(value);
                        Log.e("TAG",feedType+"加载成功!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getFalse(feedType+"加载加载失败:"+e.getMessage());
                        Log.e("TAG",feedType+"加载失败:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getShaFaData(int pageCount,String feedType,String feedId,CallBack callBack){
        apiService
                .getShaFaData(pageCount,feedType,feedId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoBean value) {
                        callBack.getTrue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getFalse(feedType+"加载加载失败:"+e.getMessage());
                        Log.e("TAG",feedType+"加载加载失败:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getFaXianData(CallBack callBack){
        apiService.getFaXianData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TextBean value) {
                        callBack.getTrue(value);
                        Log.e("TAG","文字加载成功!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getFalse("文字加载失败!"+e.getMessage());
                        Log.e("TAG","文字加载失败!"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
