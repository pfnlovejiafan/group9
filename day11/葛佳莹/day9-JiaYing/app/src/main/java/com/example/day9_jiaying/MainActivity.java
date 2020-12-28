package com.example.day9_jiaying;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day9_jiaying.Info.One;
import com.example.day9_jiaying.adapter.RevAdapter;
import com.example.day9_jiaying.bean.BannerInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private SearchView searchview;
    private LinearLayout ll_Main;
    private RecyclerView rv_list;
    private ArrayList<BannerInfo.DataBean> dataBeans;
    private RevAdapter revAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        searchview = (SearchView) findViewById(R.id.searchview);
        ll_Main = (LinearLayout) findViewById(R.id.ll_Main);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        dataBeans = new ArrayList<>();
        ArrayList<One> ones = new ArrayList<>();
        ones.add(new One(R.drawable.two));
        ones.add(new One(R.drawable.three));
        revAdapter = new RevAdapter(dataBeans, ones,this);

        rv_list.setAdapter(revAdapter);


        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerInfo bannerInfo) {
                        List<BannerInfo.DataBean> data = bannerInfo.getData();
                        dataBeans.addAll(data);
                        revAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}