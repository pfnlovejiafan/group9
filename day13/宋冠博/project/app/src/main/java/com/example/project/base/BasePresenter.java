package com.example.project.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 1.链接M层和V层  2.处理业务逻辑  3.断开网络请求开关
 *
 * @param <V>
 * @param <T>
 */
// V extends IBaseView 代表V层的实现类
public  class BasePresenter<V extends IBaseView<T>, T> implements IBasePresenter<T> {
// TODO java里面 四种引用 强引用 弱引用 软引用 虚引用

    //弱引用
    protected WeakReference<V> mView;


    //网络开关的容器
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    //添加网络开关
    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }


    //1.用弱引用修饰V层，方便gc回收 2.P层关联V层
    protected void attachView(V view) {
        mView = new WeakReference<V>(view);
    }

    // 1.释放资源 2，让P层断开网络请求
    protected void detachView() {
        //TODO 释放资源
        if (mView != null) {
            mView.clear();
            mView = null;
        }
        //TODO P层断开网络请求
        deleteDisposable();
    }

    private void deleteDisposable() {
        //网络开关容器不等于空，并且没有被断开时 断开并清空
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
    }


    @Override
    public void start() {
        //TODO 处理业务逻辑
    }

    @Override
    public void start(T... t) {
        //TODO 处理业务逻辑
    }
}
