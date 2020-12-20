package com.example.day5_jiaying1;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5_jiaying1.Info.Preson;
import com.example.day5_jiaying1.adapter.RevAdapter;
import com.example.day5_jiaying1.bean.BannerInfo;

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
    private ArrayList<BannerInfo.DataBean> mBannerlist;
    private ArrayList<Preson> pers;
    private RevAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
      /*  inData();*/
    }

 /*   private void inData() {

    }
*/

    private void initView() {
        searchview = (SearchView) findViewById(R.id.searchview);
        ll_Main = (LinearLayout) findViewById(R.id.ll_Main);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

         /*rv_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
*/


         rv_list.setLayoutManager(new LinearLayoutManager(this));
     /*   LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_list.setLayoutManager(manager);*/

        //创建数据源
                mBannerlist = new ArrayList<>();
               /* pers = new ArrayList<>();

            pers.add(new Preson(R.drawable.zan,"每日推荐"));
            pers.add(new Preson(R.drawable.game,"飞花令"));
            pers.add(new Preson(R.drawable.shi,"诗歌社群"));
            pers.add(new Preson(R.drawable.pai,"排行榜"));
            pers.add(new Preson(R.drawable.hui,"会员社区"));*/


         //绑定适配器
        adapter = new RevAdapter(mBannerlist, this);
        rv_list.setAdapter(adapter);


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
                        mBannerlist.addAll(data);
                        adapter.notifyDataSetChanged();
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