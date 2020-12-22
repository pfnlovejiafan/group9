package com.example.myapplication.bean;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.bean.BasePresenter;
import com.example.myapplication.bean.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragement<P extends BasePresenter,T> extends Fragment implements IBaseView<T> {
    private P mPresenter;
    private Unbinder bind;

    @Override
    public void ShowOk(T t) {

    }

    @Override
    public void ShowNo(String string) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId=getLayoutId();
        View view=null;
        if (layoutId!=0){
            view = inflater.inflate(layoutId, null);
            bind = ButterKnife.bind(this, view);
        }
        mPresenter=createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
        return view;
    }

    protected abstract P createPresenter();
    protected P getmPresenter(){
        if (mPresenter!=null){
            return mPresenter;
        }
        return null;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();

    }

    protected void initData() {

    }

    protected abstract void init();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind!=null){
            bind.unbind();
        }
        if (mPresenter!=null){
            mPresenter.datachView();
            mPresenter=null;
        }

    }
}
