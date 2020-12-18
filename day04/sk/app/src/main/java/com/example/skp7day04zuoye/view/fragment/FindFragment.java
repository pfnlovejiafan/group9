package com.example.skp7day04zuoye.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skp7day04zuoye.R;
import com.example.skp7day04zuoye.adapter.HuoDongRVAdapter;
import com.example.skp7day04zuoye.model.bean.HuodongBean;
import com.example.skp7day04zuoye.presenter.Presenter;
import com.example.skp7day04zuoye.view.IView;
import com.example.skp7day04zuoye.view.activity.PaiHangBangActivity;
import com.example.skp7day04zuoye.view.activity.ShetuanActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFragment extends Fragment implements IView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.tv_title_huodong)
    TextView tvTitleHuodong;
    @BindView(R.id.tab_rv)
    TabLayout tabRv;
    @BindView(R.id.rv_item)
    RecyclerView rvItem;
    @BindView(R.id.cl)
    ConstraintLayout cl;
    @BindView(R.id.rv_huodong)
    RecyclerView rvHuodong;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private Context context;
    private ArrayList<HuodongBean.DataBean> huodonglist;
    private HuoDongRVAdapter dongRVAdapter;

    public FindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        Presenter presenter = new Presenter(this);
        presenter.getHuodont();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void initView() {
        tab.addTab(tab.newTab().setText("袍子").setIcon(R.drawable.gray_radius));
        tab.addTab(tab.newTab().setText("社团").setIcon(R.drawable.gray_radius));
        tab.addTab(tab.newTab().setText("排行榜").setIcon(R.drawable.gray_radius));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    startActivity(new Intent(context, ShetuanActivity.class));
                }

                if (tab.getPosition() == 2) {
                    startActivity(new Intent(context, PaiHangBangActivity.class));
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvHuodong.setLayoutManager(layoutManager);
        huodonglist = new ArrayList<>();
        dongRVAdapter = new HuoDongRVAdapter(huodonglist, context);
        rvHuodong.setAdapter(dongRVAdapter);



        new RvAdapter();
        rvItem.setLayoutManager(new LinearLayoutManager(context));
       // rvItem.setAdapter()

    }

    @Override
    public void getTrue(Object object) {
        if (object instanceof HuodongBean) {
            HuodongBean bean = (HuodongBean) object;
            huodonglist.addAll(bean.getData());
            dongRVAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getFalse(String error) {

    }
}