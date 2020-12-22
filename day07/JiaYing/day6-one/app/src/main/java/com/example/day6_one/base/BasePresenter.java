package com.example.day6_one.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 这是P层的基类
 * P层的作用：连接M层和V层   处理业务逻辑
 * 注：之前连接M层和V层  是通过构造方法 如今需要通过泛型来进行关联 V extends IBaseView<T>
 *     这个泛型、代表V层的一个实现类
 *
 * 此时P层的作用：
 *     用于关联M层和V层  先关联的V层(里面将V层给new出来、并且用弱引用修饰)
 *     紧接着关联完后、执行释放资源+让P层断开网络请求
 *
 *     protected权限修饰符的意思是：仅限子类能够进行访问
 */
public class BasePresenter<V extends IBaseView<T>,T> implements IPresenter<T>{
//Java有四种引用方法：强、弱、软、需
 /*   private V mView;*/
    //V层的实现类  使用弱引用
    private WeakReference<V> mView;


    //这是一个网络开关的容器
    //然后添加进断开网络请求的方法里面
    protected CompositeDisposable mCompositeDisposable=
            new CompositeDisposable();


    /**
     * 以下方法：protected权限修饰符：仅限子类能够访问、其他外面的类是无法进行访问的
     */
    //添加网络开关
    protected void addDisposable(Disposable disposable){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.add(disposable);
        }
    }


    /**
     * attachView  关联
     */
    //此方法：是用来实例化V层的  将V层装到弱引用里面
    // 弱引用：可保证内存泄露  当gc碰到它、可能就会回收它
    //作用：1用弱引用修饰V层  方便Gc回收  2P层关联V层
    protected void attachView(V view){
        mView=new WeakReference<V>(view);
    }


    /**
     *dettachView 解除关联
     */
    //作用：1释放资源  2让P层断开网络请求
    //使弱引用释放
    protected void dettachView(){

        //1释放资源
        if(mView!=null){
            mView.clear();
            mView=null;
        }

        //2让P层断开网络请求
        deletedisPosable();
    }


    //断开网络请求的方法
    private void deletedisPosable() {
        if(mCompositeDisposable!=null && !mCompositeDisposable.isDisposed()){
            //如果网络容器不为空  并且 网络容器未断开
            //则执行断开、清空操作
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable=null;
        }
    }


    //重写P层的两个有参无参的方法
    @Override
    public void start() {
        //进行处理业务逻辑
    }

    @Override
    public void start(T... t) {
        //进行处理业务逻辑
    }
}
