package com.example.project.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.HomeActivity;
import com.example.project.R;
import com.example.project.base.BaseActivity;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.ChooseRvAdapter;
import com.example.project.mvp.model.entity.Choose_courseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Choose_courseActivity extends BaseActivity {


    @BindView(R.id.choose_rv)
    RecyclerView mChooseRv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        mChooseRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        ChooseRvAdapter chooseRvAdapter = new ChooseRvAdapter(getRvData());
        mChooseRv.setAdapter(chooseRvAdapter);

        findViewById(R.id.btn_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose_courseActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<Choose_courseBean> getRvData() {
        List<Choose_courseBean> rvList = new ArrayList<>();

        List<String> courseList1 = new ArrayList<>();
        courseList1.add("基金从业");
        courseList1.add("银行从业");
        courseList1.add("期贷从业");
        courseList1.add("证券从业");

        List<String> courseList3 = new ArrayList<>();
        courseList3.add("注册会记师");
        courseList3.add("会记职称");
        courseList3.add("经济师考试");
        courseList3.add("证券从业");

        List<String> courseList4 = new ArrayList<>();
        courseList4.add("CMA");

        List<String> courseList5 = new ArrayList<>();
        courseList5.add("教师资格");

        List<String> courseList6 = new ArrayList<>();
        courseList6.add("建造师");

        rvList.add(new Choose_courseBean("热门选课",courseList1));
        rvList.add(new Choose_courseBean("金融学院",courseList1));
        rvList.add(new Choose_courseBean("财会学院",courseList3));
        rvList.add(new Choose_courseBean("国际证书",courseList4));
        rvList.add(new Choose_courseBean("职业资格",courseList5));
        rvList.add(new Choose_courseBean("建筑工程",courseList6));

        return rvList;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_course;
    }

}