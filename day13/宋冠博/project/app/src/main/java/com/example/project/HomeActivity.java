package com.example.project;

import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


import com.example.project.base.App;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.HomeActVpAdapter;
import com.example.project.manager.GlobalManager;
import com.example.project.mvp.model.constants.Constants;
import com.example.project.mvp.ui.fragment.ChooseFragment;
import com.example.project.mvp.ui.fragment.HomeFragment;
import com.example.project.mvp.ui.fragment.HomeFragment2;
import com.example.project.mvp.ui.fragment.MeFragment;
import com.example.project.mvp.ui.fragment.StudyFragment;
import com.example.project.thread.ThreadPoolManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.home_act_vp)
    ViewPager homeActVp;
    @BindView(R.id.home_act_tab)
    TabLayout homeActTab;


    private FragmentManager mManager;
    private int[] mTabs = {R.string.home_tab_home, R.string.home_tab_choose, R.string.home_tab_study, R.string.home_tab_me};

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {


//        getSupportFragmentManager().beginTransaction().replace(R.id.root_cl, new HomeFragment()).commit();
        mManager = getSupportFragmentManager();
        HomeActVpAdapter homeActVpAdapter = new HomeActVpAdapter(mManager, getFragments(), mTabs);
        homeActVp.setAdapter(homeActVpAdapter);

        homeActTab.setupWithViewPager(homeActVp);


        for (int i = 0; i < mTabs.length; i++) {
            homeActTab.getTabAt(i).setIcon(R.drawable.ic_tab).setText(mTabs[i]);
        }

    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ChooseFragment());
        fragments.add(new StudyFragment());
        fragments.add(new MeFragment());


        return fragments;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
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