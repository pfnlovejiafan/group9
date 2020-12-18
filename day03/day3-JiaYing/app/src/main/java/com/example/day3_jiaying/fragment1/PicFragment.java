package com.example.day3_jiaying.fragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day3_jiaying.R;
import com.example.day3_jiaying.View.IView;
import com.example.day3_jiaying.adapter.PicRevAdapter;
import com.example.day3_jiaying.bean.PicInfo;
import com.example.day3_jiaying.presenter.PicPresenter;

import java.util.ArrayList;
import java.util.List;


public class PicFragment extends Fragment implements IView {

    private RecyclerView pic_rv;
    private ArrayList<PicInfo.DataBeanX.DataBean> twolist;

    private PicPresenter picPresenter;
    private PicRevAdapter adapter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pic, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
        initData();
    }

    private void initData() {
        picPresenter = new PicPresenter(this);
        picPresenter.start();
    }

    private void initView(View view) {
        pic_rv=view.findViewById(R.id.pic_rv);
        pic_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //创建数据源
        twolist = new ArrayList<>();
        //创建适配器
        adapter1 = new PicRevAdapter( twolist, getContext());
        pic_rv.setAdapter(adapter1);
    }

    @Override
    public void showokUi(Object obj) {
        if(obj instanceof PicInfo){
            PicInfo picInfo= (PicInfo) obj;
            //子集合
            List<PicInfo.DataBeanX.DataBean> data1 = picInfo.getData().getData();
            twolist.addAll(data1);
            adapter1.notifyDataSetChanged();
        }
    }

    @Override
    public Void shownoUi(String msg) {
        Toast.makeText(getContext(), "请求数据失败"+msg, Toast.LENGTH_SHORT).show();
        return null;
    }
}