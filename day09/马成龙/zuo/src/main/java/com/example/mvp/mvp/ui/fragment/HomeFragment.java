package com.example.mvp.mvp.ui.fragment;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.apader.Apader;
import com.example.mvp.base.BaseFragment;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.bean.HuoBean;
import com.example.mvp.bean.ListBean;
import com.example.mvp.mvp.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {


    @BindView(R.id.rec)
    RecyclerView rec;
    private Apader apader;

    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter ( this );
    }

    @Override
    protected void init() {
        List<ListBean> listBeans=new ArrayList<> (  );
        listBeans.add (new ListBean (  R.drawable.yeu1) );
        listBeans.add ( new ListBean ( R.drawable.yue2 ) );
        listBeans.add ( new ListBean ( R.drawable.yue3 ) );
        listBeans.add ( new ListBean ( R.drawable.yue4 ) );
        listBeans.add ( new ListBean ( R.drawable.yue5 ) );
        rec.setLayoutManager ( new LinearLayoutManager ( getContext () ) );
        apader = new Apader (getContext (),listBeans);
        rec.setAdapter ( apader );

    }

    @Override
    protected void initData() {
        // 1.V层向P层发送请求数据的指令
        getmPresenter ().start ();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    //成功更新UI
    @Override
    public void onScuccess(Object o) {
        if (o instanceof List){
            List<HuoBean.DataBean> list= (List<HuoBean.DataBean>) o;
            apader.addlist ( list );
            apader.notifyDataSetChanged ();


        }

    }

    //失败更新UI
    @Override
    public void onError(String msg) {

    }
}