package com.example.day6_one.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 此类是Fragment的所有基类  fragment都要继承它
 * 里面的类定义成抽象是因为大部分Fragment里面都需要重写这些方法
 */
public  abstract class BaseFragmentActivity<P extends BasePresenter,T> extends Fragment implements IBaseView<T> {

    private Unbinder mbind;

    //P层对象
    private P mPresenter;


    //fragment中的共有方法如下：

    //用来加载布局
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载控件
        int layoutId =getLayoutId();
        View view=null;
        if(layoutId!=0){
            view = inflater.inflate(layoutId,null);
            //黄油刀绑定  将view绑定到当前fragment里面得到返回值
            //然后去下面解绑
            mbind = ButterKnife.bind(this, view);
        }

      //创建P层对象
        mPresenter=createPresenter();
        if(mPresenter!=null){
            //进行关联
            mPresenter.attachView(this);
        }




        //最后将view返回
        return view;
    }

    protected abstract P createPresenter();
    //向外界提供一个getP层对象的一般方法
    protected  P getmPresenter(){
        if(mPresenter!=null){
            return mPresenter;
        }
        return null;  //并去下面解绑
    }



    //重写onViewCreated方法  它不是fragment生命周期方法 但是走完onCreateView、它会接着走onViewCreated方法
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置布局管理等资源
        init();
        //加载数据的方法
        initData();
    }

    protected abstract void initData();

    protected abstract void init();


    //抽象类
    protected abstract int getLayoutId();

    //重写了两个更新UI的两个方法
    @Override
    public void onScuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }



    //销毁视图的方法 并解除绑定
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mbind!=null){
            mbind.unbind();
        }
        if(mPresenter!=null){
            mPresenter.dettachView();
            mPresenter=null;
        }
    }
}
