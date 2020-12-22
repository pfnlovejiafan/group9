package com.example.p7mvp.mvp.presenter;

import com.example.p7mvp.base.BasePresenter;
import com.example.p7mvp.callBcak.RxCallBack;
import com.example.p7mvp.mvp.model.Model;
import com.example.p7mvp.mvp.ui.activity.MainActivity;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter<T> extends BasePresenter {

    @Inject
    public Model model;

    private MainActivity view;

    public HomePresenter(MainActivity mainActivity) {
        this.view = mainActivity;
        model = new Model();
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return super.getCompositeDisposable();
    }

    @Override
    public void start() {
        model.getData("", new RxCallBack<T>() {
            @Override
            public void getDisposable(Disposable disposable) {

            }

            @Override
            public void resultSucceed(T t) {
                view.onSuccess(t);
            }

            @Override
            public void error(Throwable error) {

            }
        });
    }
}
