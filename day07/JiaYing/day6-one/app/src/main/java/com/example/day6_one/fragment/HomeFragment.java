package com.example.day6_one.fragment;

import android.util.Log;
import android.widget.TextView;

import com.example.day6_one.R;
import com.example.day6_one.base.BaseFragmentActivity;
import com.example.day6_one.base.BasePresenter;

import butterknife.BindView;

/**
 * 在这里面请求数据
 */
public class HomeFragment extends BaseFragmentActivity {

    @BindView(R.id.home_tv_show)
    TextView homeTvShow;

    //这是P层
    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter(this);
    }


  //请求数据
    @Override
    protected void initData() {
       //先V层向P层发送指令
        getmPresenter().start();
        //返回HomeFragment的P层进行请求数据
    }

    @Override
    protected void init() {

    }

    //获取布局ID
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    //重写获取数据的两个方法
    @Override
    public void onScuccess(Object o) {
        String str= (String) o;
        Log.e("TAG","请求成功："+str);
    }
    @Override
    public void onError(String msg) {
        super.onError(msg);
        Log.e("TAG","请求失败："+msg);
    }
}