package com.example.day3_jiaying.fragment1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day3_jiaying.ApiService;
import com.example.day3_jiaying.R;
import com.example.day3_jiaying.View.IView;
import com.example.day3_jiaying.adapter.TVAdapter;
import com.example.day3_jiaying.bean.PicInfo;
import com.example.day3_jiaying.bean.TVBean;
import com.example.day3_jiaying.presenter.TVPicPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvFragment extends Fragment {


    private RecyclerView tv_rv;
    private ArrayList<PicInfo.DataBeanX.DataBean> dataBeans;
    private TVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

   @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        tv_rv=view.findViewById(R.id.tv_rv);
        tv_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBeans = new ArrayList<>();

        adapter = new TVAdapter(dataBeans, getContext());
        tv_rv.setAdapter(adapter);
        initData();
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTV("12","video")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PicInfo value) {
                        List<PicInfo.DataBeanX.DataBean> data = value.getData().getData();
                        dataBeans.addAll(data);
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