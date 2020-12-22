package com.example.myapplication.bean;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView<T>, T> implements IPresenter<T> {
    private WeakReference<V> mView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void start() {

    }

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }
    }
    protected void attachView(V view){
        mView=new WeakReference<V>(view);
    }
    protected void datachView(){
        if (mView!=null){
            mView.clear();
            mView=null;
        }
        if (compositeDisposable!=null&&compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
            compositeDisposable.clear();
            compositeDisposable=null;
        }
    }
    @Override
    public void start(T... t) {

    }
}
