package com.example.p7mvp.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.p7mvp.manager.ContainManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

                //TODO Activity 基类    抽取Activity公有方法
public abstract class BaseActivity<P extends BasePresenter,T> extends AppCompatActivity implements IBaseView<T>{
    private Unbinder bind;
    private P presenter;

    //初始化布局
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != 0)
            setContentView(layoutId);
        bind = ButterKnife.bind(this);

        // 把Activity对象添加到自定义的布局管理器中
        ContainManager.getContainManager().addActivity(this);

        //创建presenter 对象方法  并传this 与presenter绑定
        presenter = createPresenter();
        if (presenter != null)
            presenter.attachView(this);
        init();
        initData();
    }

    protected abstract P createPresenter();

    protected P getPresenter(){
        if (presenter != null)
           return presenter;
        return null;
    }

    protected void onClick(View view){

    }

    protected  void initData(){
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null)
           bind.unbind();

        if (presenter != null){
            //调用资源回收 断开网络 方法
            presenter.detachView();
            presenter = null;
        }

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }
}
