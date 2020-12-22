package com.example.myapplication.bean;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.manager.ContainManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity <P extends BasePresenter,T> extends AppCompatActivity implements IBaseView<T>{

    private Unbinder bind;
    private P mPrasenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId= getLayoutId();
        if (layoutId!=0){
            setContentView(layoutId);
        }
        bind = ButterKnife.bind(this);
        ContainManager.getContainManager().addActivity(this);
        mPrasenter = createPrasenter();
        if (mPrasenter!=null){
            mPrasenter.attachView(this);
        }
        init();
        initData();
    }

    protected abstract P createPrasenter();

    protected P getmPrasenter(){
        if (mPrasenter!=null){
            return mPrasenter;
        }
        return null;
    }

    protected  void initData(){};
    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void ShowOk(T t) {

    }

    @Override
    public void ShowNo(String string) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        if (mPrasenter!=null){
//            mPrasenter.detachView();
            mPrasenter.datachView();
            mPrasenter=null;
        }
    }
}
