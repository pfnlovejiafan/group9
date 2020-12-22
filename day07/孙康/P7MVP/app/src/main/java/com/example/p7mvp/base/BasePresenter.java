package com.example.p7mvp.base;

import java.lang.ref.WeakReference;
import io.reactivex.disposables.CompositeDisposable;

                //TODO Presenter基类   M 与 V 之间的桥梁  资源回收与断开网络
public class BasePresenter<View extends IBaseView<T>, T> implements IBasePresenter<T> {
    private WeakReference<View> mView;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    //TODO 弱引用修饰P层 方便回收   P层关联V层方法
    protected void attachView(View view) {
        mView = new WeakReference<View>(view);
    }

    public CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable != null)
            return compositeDisposable;
        return null;
    }

    //TODO 释放资源 断开网络
    protected void detachView() {
        //TODO 释放资源
        if (mView != null) {
            mView.clear();
            mView = null;
        }

        //TODO 断开网络
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }


    //TODO 执行事务 处理逻辑
    @Override
    public void start() {
    }


    //TODO 执行多个事务 处理多个逻辑
    @Override
    public void start(T... t) {
    }
}
