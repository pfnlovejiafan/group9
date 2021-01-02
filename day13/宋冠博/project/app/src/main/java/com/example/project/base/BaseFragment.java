package com.example.project.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter, T> extends Fragment implements IBaseView<T> {

    private Unbinder mBind;
    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载布局
        int layoutId = getLayoutId();
        View view = null;
        if (layoutId != 0) {
            view = inflater.inflate(layoutId, null);
            mBind = ButterKnife.bind(this, view);
        }

        //创建P层对象，并关联V层
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        return view;
    }

    protected abstract P createPresenter();

    //返回P对象，供外界使用(获取P层对象)
    protected P getPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return null;
    }

    /**
     * 不是fragment声明周期方法
     * 调用 onCreateView() 方法后调用
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化view
        init();
        //初始化数据
        initData();
    }

    protected void initData() {
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void onSucceed(T t) {

    }

    @Override
    public void onFiled(String errorMsg) {

    }

    //释放资源
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
