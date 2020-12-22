package com.example.p7mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//TODO Fragment 基类    抽取Fragment公有方法

public abstract class BaseFragment<P extends BasePresenter, T> extends Fragment implements IBaseView<T> {
    private Unbinder bind;
    private P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();

        View view = null;
        if (layoutId != 0) {
            view = inflater.inflate(layoutId, null);
            bind = ButterKnife.bind(this, view);
        }

        //创建presenter 对象方法  并传this 与presenter绑定
        if (presenter == null)
            presenter = createPresenter();
        if (presenter != null)
            presenter.attachView(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
        onClick(view);
        onLongClick(view);
    }

    //TODO 获取P对象
    protected P getPresenter() {
        if (presenter != null)
            return presenter;
        return null;
    }

    //TODO 优化
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null)
            bind.unbind();

        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    //TODO 创建Presenter
    protected abstract P createPresenter();

    //TODO 初始化布局
    protected abstract void init();

    //TODO 获取布局ID
    protected abstract int getLayoutId();

    //TODO 获取数据
    protected void initData() {
    }

    //TODO 点击监听
    protected void onClick(View view) {
    }

    //TODO 长按监听
    protected  void onLongClick(View view){
    }

    @Override
    public void onSuccess(T t) {
    }

    @Override
    public void onError(String msg) {
    }
}
