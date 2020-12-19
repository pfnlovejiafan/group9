package com.example.demo_03.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_03.R;
import com.example.demo_03.bean.DataaBean;
import com.example.demo_03.model.Apiservice;

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
public class Fu_AFragment extends Fragment {

    private RecyclerView mRv;
    private ArrayList<DataaBean.DataBean.ListBean> list;
    private RvRvAdapter rvRvAdapter;
    private int nb;

    public Fu_AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fu__a, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        Bundle arguments = getArguments();
        nb = arguments.getInt("nb");
        rvRvAdapter = new RvRvAdapter(getContext(),list);
        mRv.setAdapter(rvRvAdapter);
        initData();
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(Apiservice.a)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Apiservice.class)
                .getData(nb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataaBean value) {
                        List<DataaBean.DataBean.ListBean> list = value.getData().getList();
                        list.addAll(list);
                        rvRvAdapter.notifyDataSetChanged();
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
