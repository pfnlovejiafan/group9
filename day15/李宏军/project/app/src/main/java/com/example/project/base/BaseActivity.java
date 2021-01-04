package com.example.project.base;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.project.R;
import com.example.project.manager.GlobalManager;
import com.example.project.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有Activity都要继承的类
 *
 * @param <T>
 */

public abstract class BaseActivity<P extends BasePresenter, T> extends AppCompatActivity implements IBaseView<T> {

    private Unbinder mBind;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏 在setContentView之前设置
        StatusBarUtil.setStatusBarColor(this,R.color.white);

        //当前Activity加载布局
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }

        mBind = ButterKnife.bind(this);

        //将所有的Activity添加到集合
        GlobalManager.getManager().addActivity(this);

        //1.创建P层对象，并关联V层
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        //初始化View
        init();
        //初始化数据
        initData();

        //监听器
        initListener();
    }

    protected void initListener() {
    }

    protected abstract P createPresenter();

    //返回P对象，供外界使用(获取P层对象)
    protected P getPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return null;

    }

    protected abstract void init();

    protected void initData() {
    }


    protected abstract int getLayoutId();

    @Override
    public void onSucceed(T t) {

    }

    @Override
    public void onFiled(String errorMsg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
