package com.example.day6_one.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.day6_one.manager.ContainManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 此类是所有Activity的一个基类 (所有Activity都要继承的类)
 * (此类是存放：Activity中共有的方法)
 * 注意：如果要实现加载数据的话、才在这里实现这个接口(里面方法是：成功/失败数据的方法)
 * 补充：若你声明为抽象的方法 那必须得重写、据情况而定
 *
 * 创建P对象像V层发送请求数据的指令   先继承P层基类 然后用变量引用
 * 再创建对象并赋值
 * 再利用P层对象调用关联的方法
 * 在创建一个获取P蹭的方法
 * /获取P层数据的方法       //进行加载数据的方法
 */
public abstract class BaseActivity<P extends BasePresenter,T> extends AppCompatActivity implements IBaseView<T> {
    private Unbinder bind;
    //Activity中共有的方法 onCreate(一般做加载布局、初始化控件操作)

    //P对象  变量引用
    private P mPresenter;


    //此方法重写的是一个参数的   若是两个参数的方法会出现白板的情况
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //进行加载布局
        int layoutId=getLayoutId();
        if(layoutId!=0)
            setContentView(layoutId);


        //(使用黄油刀)初始化控件 (注：父类中绑定一次就行、子类中就不需要进行绑定了)
        //得到返回 值 并作为全局变量
        bind = ButterKnife.bind(this);

        //调用打开、关闭方法
        //将所有的Activity添加到集合里面
        ContainManager.getmManager().addActivity(this);

        //1.创建P层对象并且关联V层
        mPresenter=createPresenter();
        //创建P对象 并赋值
        if(mPresenter!=null)
           //利用P层对象调用关联的方法
        mPresenter.attachView(this);



        //此方法可以加载Recyclerview等 添加布局管理器、分割线等
        init();

        //加载数据
        initData();
    }

    protected abstract P createPresenter();
    protected P getmPresenter(){
        //并将P层对象、返回过去
        //否则无法进行调用
        if(mPresenter!=null){
            return mPresenter;
        }
       return null;
    }

    protected abstract void initData();

    protected abstract void init();

/*    //还有一些关于注解黄油刀的多种点击事件
    protected abstract void clickButterKnife(View view);*/

/*    @OnClick({R.id.btn1,})
    protected  void clickButterKnife(){

    }*/



    protected abstract int getLayoutId();

    //更新UI的两个方法
    @Override
    public void onScuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }


    //一般在Destory中做销毁的方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //同样在父类中解绑一次、子类中也不需要进行解绑了
        if(bind!=null){
            bind.unbind();
        }

        //释放V层、并断开网络请求
        if(mPresenter!=null){
            mPresenter.dettachView();
            mPresenter=null;
        }
    }
}
