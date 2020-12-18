package com.example.day4_jiaying.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day4_jiaying.R;
import com.example.day4_jiaying.View.IView;
import com.example.day4_jiaying.adapter.PaoRevAdapter;
import com.example.day4_jiaying.bean.PaoInfo;
import com.example.day4_jiaying.presenter.PaoPresenter;

import java.util.ArrayList;
import java.util.List;


public class PaoFragment extends Fragment implements IView {


    private RecyclerView pao_rv;
    private ArrayList<PaoInfo.DataBean> dataBeans;
    private PaoRevAdapter adapter;
    private PaoPresenter paoPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pao, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview(getView());
        initData();
    }

    private void initData() {
        paoPresenter = new PaoPresenter(this);
        paoPresenter.start();
    }

    private void initview(View view) {
        pao_rv=view.findViewById(R.id.pao_rv);
        //设置横向列表
    /*     LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pao_rv.setLayoutManager(layoutManager);*/
    pao_rv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

   /*   pao_rv.setLayoutManager(new LinearLayoutManager(getContext()));*/
        //创建数据源
        dataBeans = new ArrayList<>();
        //创建适配器
        adapter = new PaoRevAdapter(dataBeans, getContext());
        pao_rv.setAdapter(adapter);
    }

    @Override
    public void showokUi(Object obj) {
        if(obj instanceof PaoInfo){
            PaoInfo paoInfo= (PaoInfo) obj;
            List<PaoInfo.DataBean> data = paoInfo.getData();
            dataBeans.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public Void shownoUi(String msg) {
        Toast.makeText(getContext(), "请求数据失败"+msg, Toast.LENGTH_SHORT).show();
        return null;
    }
}