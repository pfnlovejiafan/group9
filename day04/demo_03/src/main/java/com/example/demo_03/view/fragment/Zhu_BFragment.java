package com.example.demo_03.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_03.R;
import com.example.demo_03.bean.ReMenBean;
import com.example.demo_03.bean.TabBean;
import com.example.demo_03.model.Apiservice;
import com.example.demo_03.prester.HomePrester;
import com.example.demo_03.view.IView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhu_BFragment extends Fragment implements IView {

    private LinearLayout m2Ll;
    private RecyclerView mRv;
    private TabLayout mATab;
    private TabLayout mTab;
    private ViewPager vp;
    private LinearLayout m3Ll;
    private HomePrester homePrester;
    private ArrayList<ReMenBean.DataBean> list;
    private RvAdapter rvAdapter;
    private ArrayList<Fragment> listt;
    private VpAdapter vpAdapter;
    private ArrayList<TabBean.DataBean> strings;

    public Zhu_BFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_zhu__b, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        m2Ll = (LinearLayout) itemView.findViewById(R.id.ll_2);
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);
        mATab = (TabLayout) itemView.findViewById(R.id.tab_a);
        mTab = (TabLayout) itemView.findViewById(R.id.tab);
        vp = (ViewPager) itemView.findViewById(R.id.vp);
        m3Ll = (LinearLayout) itemView.findViewById(R.id.ll_3);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        rvAdapter = new RvAdapter(getContext(), list);
        mRv.setAdapter(rvAdapter);


        homePrester = new HomePrester(this);
        homePrester.start();
        new ArrayList<>();

        mTab.addTab(mTab.newTab().setText("袍子").setIcon(R.drawable.ic_icon_8));
        mTab.addTab(mTab.newTab().setText("社团").setIcon(R.drawable.ic_icon_10));
        mTab.addTab(mTab.newTab().setText("排行榜").setIcon(R.drawable.ic_icon_12));



        initData();

        strings = new ArrayList<>();
        listt = new ArrayList<>();

    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(Apiservice.a)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Apiservice.class)
                .getTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean value) {
                        List<TabBean.DataBean> data = value.getData();
                        strings.addAll(data);
                        for (int i = 0; i < strings.size(); i++) {
                            Fu_AFragment fu_aFragment = new Fu_AFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("nb",strings.get(i).getType());
                            fu_aFragment.setArguments(bundle);
                            listt.add(fu_aFragment);
                        }
                        vpAdapter = new VpAdapter(getChildFragmentManager(),strings,listt);
                        vp.setAdapter(vpAdapter);
                        mATab.setupWithViewPager(vp);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void ShowChenggong(Object ob) {
        if (ob instanceof ReMenBean) {
            ReMenBean ob1 = (ReMenBean) ob;
            List<ReMenBean.DataBean> data = ob1.getData();
            list.addAll(data);
            rvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowShibai(String str) {
        Log.e("TAG", "错误"+str );
    }
}
