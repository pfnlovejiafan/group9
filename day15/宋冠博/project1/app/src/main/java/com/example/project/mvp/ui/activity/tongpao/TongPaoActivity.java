package com.example.project.mvp.ui.activity.tongpao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.manager.GlobalManager;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.ui.fragment.home3.CourseFragment3;
import com.example.project.mvp.ui.fragment.home3.HomeFragment3;
import com.example.project.mvp.ui.fragment.home3.LearnFragment3;
import com.example.project.mvp.ui.fragment.home3.MineFragment3;
import com.example.project.mvp.ui.fragment.tongpao.TPHomeFragment;
import com.example.project.thread.ThreadPoolManager;
import com.example.project.utils.StatusBarUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class TongPaoActivity extends BaseActivity {


    @BindView(R.id.tongpao_act_frame)
    FrameLayout mTongPaoActFrame;
    @BindView(R.id.tongpao_act_nav)
    BottomNavigationView mTongPaoActNav;

    private Fragment[] mFragments = new Fragment[4];

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void init() {

        //设置底部虚拟按键导航条的颜色
        getWindow().setNavigationBarColor(Color.BLACK);

//        //设置全屏(隐藏状态栏和Toolbar)
//        StatusBarUtil.setFullScreen(this);

//        //设置沉浸式状态栏(状态栏透明，内容填充到状态栏下方)
//        StatusBarUtil.setStatusBarTrans(this);

//        //隐藏状态栏及底部虚拟按键，屏幕顶部下划显示
//        StatusBarUtil.hideShowStatusBar(this);

        //1. 去掉下面图标的小黑块 2.恢复原来的图标颜色
        mTongPaoActNav.setItemIconTintList(null);
        //让第0个tab默认选中
        mTongPaoActNav.setSelectedItemId(R.id.nav_home);
        //让第几个tab默认选中
//        mHomeActNav.setSelectedItemId(mHomeActNav.getMenu().getItem(1).getItemId());

        mFragments[0] = new TPHomeFragment();
        mFragments[1] = new CourseFragment3();
        mFragments[2] = new LearnFragment3();
        mFragments[3] = new MineFragment3();

        // add()  hide()  show()
        // hide() show() 对fragment生命周期方法没有任何影响，只对onHiddenChanged有影响
        // hidden为true表示该fragment被隐藏
        // hidden为false表示该fragment被隐藏
        // 用onHiddenChanged请求加载数据

        //用于将所有的fragment对象添加到fragmentManager中，默认显示第0个fragment
        initLoadFragment(R.id.tongpao_act_frame, 0);

    }

    private void initLoadFragment(int containerId, int showFragmentPosition) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.length; i++) {   //获取每个fragment的名字
            ft.add(containerId, mFragments[i], mFragments[i].getClass().getSimpleName());
            // tag标记 根据tag标记获取对应的fragment
            // getSupportFragmentManager().findFragmentByTag("a3");

            if (i != showFragmentPosition) {
                ft.hide(mFragments[i]);
            } else {
                ft.show(mFragments[i]);
            }
        }
        //提交事务时，避免FragmentManager出现异常
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void initListener() {

        mTongPaoActNav.setOnNavigationItemSelectedListener(itemSelListener);

    }

    private int mFragTab = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:

                switchFragment(mFragments[0], mFragments[mFragTab]);
                mFragTab = 0;
                break;
            case R.id.nav_course:

                switchFragment(mFragments[1], mFragments[mFragTab]);
                mFragTab = 1;

                break;
            case R.id.nav_learn:

                switchFragment(mFragments[2], mFragments[mFragTab]);
                mFragTab = 2;

                break;
            case R.id.nav_mine:

                switchFragment(mFragments[3], mFragments[mFragTab]);
                mFragTab = 3;

                break;
        }
        return true;
    };

    //显示下一个，隐藏上一个
    private void switchFragment(Fragment showFragment, Fragment hideFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (showFragment != hideFragment) {
            ft.hide(hideFragment).show(showFragment);
        }
        ft.commitAllowingStateLoss();
    }

    //当前窗口焦点发生变化(当你触摸到屏幕)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//当窗口获取焦点并且版本大于4.4
            StatusBarUtil.hideShowStatusBar(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tong_pao;
    }

    //监听返回键
    private boolean mIsExit;
//    private Handler mHandler = new Handler();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //KeyEvent.KEYCODE_BACK：返回键
            //点击返回键
            //TODO 2秒之内连续点击退出程序，如果第二次点击超出2秒，Toast提示再次点击退出程序
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(App.getContent(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //两秒后，把变量值改为true
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mIsExit = false;
//                    }
//                }, 2000);

                //两秒后，把变量值改为true
                ThreadPoolManager.getThreadPool(Constants.SCHEDULED_THREADPOOL)
                        .executorTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit = false;
                            }
                        }, 2, TimeUnit.SECONDS);

            } else {
                //TODO 退出
                GlobalManager.getManager().clearActivity();
                Toast.makeText(App.getContent(), "退出程序", Toast.LENGTH_SHORT).show();
            }

        }
        //涉及事件分发
        return false;
    }
}