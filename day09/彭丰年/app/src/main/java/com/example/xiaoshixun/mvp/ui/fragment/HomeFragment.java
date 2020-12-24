package com.example.xiaoshixun.mvp.ui.fragment;

import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaoshixun.R;
import com.example.xiaoshixun.base.App;
import com.example.xiaoshixun.base.BaseFragment;
import com.example.xiaoshixun.base.BasePresenter;
import com.example.xiaoshixun.base.JsonBean;
import com.example.xiaoshixun.mvp.presenter.HomeFrgPresenter;

import java.util.ArrayList;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private MyRcyAdapter myRcyAdapter;
    private ArrayList<JsonBean> list;


    @Override
    protected BasePresenter createPresenter() {
        return new HomeFrgPresenter(this);
    }

    @Override
    protected void initData() {
        getmPresenter().start();
    }

    @Override
    protected void init() {
        rcy.setLayoutManager(new LinearLayoutManager(App.context(),LinearLayoutManager.HORIZONTAL,false));
        list = new ArrayList<>();
        list.add(new JsonBean(R.drawable.icon_marka,"首页1"));
        list.add(new JsonBean(R.drawable.icon_marka,"首页2"));
        list.add(new JsonBean(R.drawable.icon_marka,"首页3"));
        list.add(new JsonBean(R.drawable.icon_marka,"首页4"));
        list.add(new JsonBean(R.drawable.icon_marka,"首页5"));
        myRcyAdapter = new MyRcyAdapter(list,App.context());
        rcy.setAdapter(myRcyAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void onScuccess(Object obj) {
        String str = (String) obj;
        Log.e("TAG", str+"================");
    }


    @Override
    public void onError(String msg) {
        Log.e("TAG", msg+"================");
    }
}