package com.example.dome04;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dome04.adapter.RodeAdapter;
import com.example.dome04.adapter.RodeNearAdapter;
import com.example.dome04.api.ApiServer;
import com.example.dome04.bean.RodeBean;
import com.example.dome04.bean.RodeNearBean;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RobeActivity extends AppCompatActivity {

    private EditText mSearchEt;
    private RelativeLayout mFoundRl;

    private RecyclerView mRodeRv;
    private RecyclerView mRodeRv2;
    private AppBarLayout mAppbar;
    private List<RodeBean.DataBean> rodeList;
    private RodeAdapter rodeAdapter;
    private List<RodeNearBean.DataBean.ListBean> rodeNearList;
    private RodeNearAdapter rodeNearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robe);
        initView();
        initData();
    }

    private void initData() {
        ApiServer apiServer = new Retrofit.Builder()
                .baseUrl(ApiServer.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServer.class);
        apiServer.getRodeBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RodeBean rodeBean) {

                        rodeList.addAll(rodeBean.getData());
                        rodeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        apiServer.getRodeNearBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RodeNearBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RodeNearBean rodeNearBean) {
                        rodeNearList.addAll(rodeNearBean.getData().getList());
                        rodeNearAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mSearchEt = (EditText) findViewById(R.id.et_search);
        mFoundRl = (RelativeLayout) findViewById(R.id.rl_found);
        mRodeRv = (RecyclerView) findViewById(R.id.rv_rode);
        mRodeRv2 = (RecyclerView) findViewById(R.id.rv_rode2);
        mAppbar = (AppBarLayout) findViewById(R.id.appbar);


        rodeList = new ArrayList<>();
        rodeAdapter = new RodeAdapter(this, rodeList);

        mRodeRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRodeRv.setAdapter(rodeAdapter);

        rodeNearList = new ArrayList<>();
        rodeNearAdapter = new RodeNearAdapter(this, rodeNearList);
        mRodeRv2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRodeRv2.setAdapter(rodeNearAdapter);
    }
}