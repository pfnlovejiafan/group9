package com.example.dome04;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dome04.adapter.CommunityAdapter;
import com.example.dome04.api.ApiServer;
import com.example.dome04.bean.CommunityBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityActivity extends AppCompatActivity {

    private EditText mSearchEt;
    private TextView mShetuanTv;
    private RecyclerView mCommunityRv;
    private List<CommunityBean.DataBean.ListBean> communityList;
    private CommunityAdapter communityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        initView();
        initData();
    }

    private void initData() {
        ApiServer apiServer = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiServer.BASE_URL)
                .build()
                .create(ApiServer.class);
        apiServer.getCommunityBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommunityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        communityList.addAll(communityBean.getData().getList());
                        communityAdapter.notifyDataSetChanged();
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
        mShetuanTv = (TextView) findViewById(R.id.tv_shetuan);
        mCommunityRv = (RecyclerView) findViewById(R.id.rv_community);

        communityList = new ArrayList<>();
        communityAdapter = new CommunityAdapter(this, communityList);

        mCommunityRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mCommunityRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mCommunityRv.setAdapter(communityAdapter);

    }
}