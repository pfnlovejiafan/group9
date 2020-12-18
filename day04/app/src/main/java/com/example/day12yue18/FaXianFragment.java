package com.example.day12yue18;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day12yue18.adapter.FaXianAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FaXianFragment extends Fragment {

    private View view;
    private RecyclerView recycler;
    private ArrayList<FaXianBean.DataBean> list;
    private ArrayList<FaXianBean.DataBean> list1;
    private FaXianAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faxian_item, null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        new Retrofit.Builder().baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class)
                .lie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FaXianBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FaXianBean faXianBean) {
                        List<FaXianBean.DataBean> data = faXianBean.getData();
                        list.addAll(data);
                        list1.addAll(data);
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

    private void initView() {
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        adapter = new FaXianAdapter(getActivity(), list,list1);
        recycler.setAdapter(adapter);
    }
}
