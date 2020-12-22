package com.example.day6_one;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.day6_one.base.BaseActivity;
import com.example.day6_one.base.BasePresenter;
import com.example.day6_one.fragment.HomeFragment;
import com.example.day6_one.manager.ContainManager;
import com.example.day6_one.manager.ThreadPoolManager;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * App
 * ContainManager
 * DBManager
 * IBaseView
 * BaseActivity
 * HomeActivity   这是activity的基类   若在父类绑定了、子类就不需要了  操作退出程序
 * BaseFragmentActivity 这是Fragment的基类
 * 接下来是P层共有的方法 定义接口 IPresenter
 * Actvity:是P层基类BasePresenter  执行了当V层已退出、则断开网络请求释放资源处理不让它在请求数据 (注意在HomeActivity里面initData()列举了一下)
 * Fragment:BaseFragmentActivity 是P层基类   然后返回HomeActivity进行请求数据
 *
 *
 *
 *  先在BaseActivity里面发送请求数据的指令
 *RxCallback
 *
 *
 * 线程池：
 * 先定义定时线程池  线程池基类  线程池管理类
 * 然后去线程池的基类 重载一个方法
 * 最后在HomeActivity里面执行就可以了
 *
 * 然后将Homemodel+p层+fragemnt删除
 * 定义一个ApiService接口
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    /*
        @BindView(R.id.tv)
        TextView tv;*/
    //变量值、未赋值代表是false
    private boolean mIsExit;
  /*  //2秒之后使用Handler定时器
    private Handler mHandler = new Handler();*/

    //使用线程池操作、Handler比较内存



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //初始化Fragment
    @Override
    protected void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_root, new HomeFragment())
                .commit();
    }

    //获取P层数据的方法
    //只有请求数据的时候、才需要P对象
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //进行加载数据的方法


    @Override
    protected void initData() {
      /*  //网络开关
        Observable.just(1,2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        //获取P层get获取P层对象数据的方法
       /* getmPresenter().start();*/
    }



   /* @Override
    protected void clickButterKnife(View view) {

    }*/

    //监听返回键方法1
/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
*/
    //监听返回键方法2
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //键盘码       ==      返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //2秒内连续点击  退出程序   如果第二次点击超过两秒了、会吐丝提示再按一次退出程序
            //!mIsExit代表是true
            /*----------------下面是两秒之前执行的---------------*/
            if (!mIsExit) {
                mIsExit = true;
                //吐丝
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

                /*----------------下面是两秒之后执行的---------------*/
           /*     //两秒之后、把变量值改为true
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);*/

           //使用线程池操作  去线程池的基类里面重载一个方法
                ThreadPoolManager.getThreadPool(ThreadPoolManager.SCHDULE_THREADPOOL)
                        .executeTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit=false;
                            }
                        },2, TimeUnit.SECONDS);

            } else {
                //退出
                ContainManager.getmManager().clearActivity();
            }
            //代表onkeyDown方法是一个事件分发 默认false即可
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }


}