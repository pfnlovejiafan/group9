package com.example.dome04.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.dome04.CommunityActivity;
import com.example.dome04.R;
import com.example.dome04.RobeActivity;
import com.example.dome04.adapter.HotActivityAdapter;
import com.example.dome04.adapter.MyAdapter;
import com.example.dome04.api.ApiServer;
import com.example.dome04.bean.Hot_ActivityBean;
import com.example.dome04.bean.NavigationBean;
import com.google.android.material.appbar.AppBarLayout;
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

public class FoundFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private ImageView mPaoziIv;
    private ImageView mShetuanIv;
    private ImageView mPaihangbangIv;
    private TextView mPaoziTv;
    private TextView mShetuanTv;
    private TextView mPaihangbangTv;
    private RelativeLayout mFoundRl;
    private RecyclerView mActivityRv;
    private AppBarLayout mAppbar;
    private ViewPager mFindVp;
    List<Hot_ActivityBean.DataBean> hot_activityList;
    private HotActivityAdapter hotActivityAdapter;
    private TabLayout mFound2Tab;
    List<Fragment> fragments;
    List<String> tabs;

    public FoundFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_found, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        ApiServer apiServer = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiServer.BASE_URL)
                .build()
                .create(ApiServer.class);
        apiServer.getHotActivityBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Hot_ActivityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Hot_ActivityBean hot_activityBean) {
                        hot_activityList.addAll(hot_activityBean.getData());
                        hotActivityAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        apiServer.getFoundNavigationBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NavigationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NavigationBean navigationBean) {
                        if (navigationBean.getData() != null && navigationBean.getData().size() > 0) {
                            tabs.clear();
                            List<NavigationBean.DataBean> data = navigationBean.getData();


//        vpFound.setAdapter(myAdapter);
//        tabFound.setupWithViewPager(vpFound);

                            for (int i = 0; i < data.size(); i++) {
                                NavigationBean.DataBean dataBean = data.get(i);
                                tabs.add(dataBean.getName());

                                HotFragment hotFragment = new HotFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("type", dataBean.getType() + "");
                                hotFragment.setArguments(bundle);
                                fragments.add(hotFragment);


                            }
                            MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(), fragments, tabs);
                            mFound2Tab.setupWithViewPager(mFindVp);
                            mFindVp.setAdapter(myAdapter);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initView(@NonNull final View itemView) {


        mFoundRl = (RelativeLayout) itemView.findViewById(R.id.rl_found);
        mActivityRv = (RecyclerView) itemView.findViewById(R.id.rv_activity);
        mAppbar = (AppBarLayout) itemView.findViewById(R.id.appbar);
        mFindVp = (ViewPager) itemView.findViewById(R.id.vp_find);
        mFound2Tab = (TabLayout) itemView.findViewById(R.id.tab_found2);
        mPaoziIv = (ImageView) itemView.findViewById(R.id.iv_paozi);
        mShetuanIv = (ImageView) itemView.findViewById(R.id.iv_shetuan);
        mPaihangbangIv = (ImageView) itemView.findViewById(R.id.iv_paihangbang);
        mPaoziTv = (TextView) itemView.findViewById(R.id.tv_paozi);
        mShetuanTv = (TextView) itemView.findViewById(R.id.tv_shetuan);
        mPaihangbangTv = (TextView) itemView.findViewById(R.id.tv_paihangbang);

        mPaoziTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RobeActivity.class));
            }
        });

        mShetuanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CommunityActivity.class));
            }
        });


        tabs = new ArrayList<>();
        fragments = new ArrayList<>();

        mFound2Tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        hot_activityList = new ArrayList<>();
        hotActivityAdapter = new HotActivityAdapter(getContext(), hot_activityList);

        mActivityRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mActivityRv.setAdapter(hotActivityAdapter);

    }
}