package com.example.day042.mvp;

import android.app.Application;

import com.example.day042.bean.JsonBean;
import com.example.day042.bean.PaoBean;
<<<<<<< HEAD
import com.example.day042.bean.SheBean;
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RelletM {
    public void Json(final Cellet cellet){
        new Retrofit.Builder()
                .baseUrl(ApiSelect.BUAT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiSelect.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonBean jsonBean) {
                        if (cellet!=null)
                            cellet.showOk(jsonBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (cellet!=null)
                            cellet.showNo(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void JsonPao(final Cellet cellet){
        new Retrofit.Builder()
                .baseUrl(ApiSelect.BUAT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiSelect.class)
                .getPao()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaoBean paoBean) {
                        if (cellet!=null)
                            cellet.showOk(paoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (cellet!=null)
                            cellet.showOk(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
<<<<<<< HEAD

    public void JsonShe(final Cellet cellet) {
        new Retrofit.Builder()
                .baseUrl(ApiSelect.BUAT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiSelect.class)
                .getShe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SheBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SheBean sheBean) {
                        if (cellet!=null){
                            cellet.showOk(sheBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (cellet!=null)
                            cellet.showNo(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
}
