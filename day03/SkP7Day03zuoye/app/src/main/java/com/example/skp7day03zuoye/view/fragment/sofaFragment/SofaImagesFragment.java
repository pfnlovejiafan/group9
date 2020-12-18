package com.example.skp7day03zuoye.view.fragment.sofaFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skp7day03zuoye.R;
import com.example.skp7day03zuoye.model.bean.ImagesBean;
import com.example.skp7day03zuoye.presenter.Presenter;
import com.example.skp7day03zuoye.view.IView;
import com.example.skp7day03zuoye.view.adapter.ImageAdapter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SofaImagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SofaImagesFragment extends Fragment implements IView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private Context context;
    private ArrayList<ImagesBean.DataBeanX.DataBean> list;
    private ImageAdapter adapter;
    private Presenter presenter;

    public SofaImagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SofaImagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SofaImagesFragment newInstance(String param1, String param2) {
        SofaImagesFragment fragment = new SofaImagesFragment();
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
        view = inflater.inflate(R.layout.fragment_sofa_images, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private int page = 1;
    private String feedType = "pics";


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void initView() {
        presenter = new Presenter(this);
        rv.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        adapter = new ImageAdapter(list, context);
        rv.setAdapter(adapter);

        presenter.getShaFaData(page,feedType);

        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getShaFaData(page,feedType);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                list.clear();
                presenter.getShaFaData(page,feedType);
            }
        });

    }

    @Override
    public void getTrue(Object object) {
        if (object instanceof ImagesBean){
            ImagesBean bean = (ImagesBean) object;
            list.addAll(bean.getData().getData());
            adapter.notifyDataSetChanged();
        }
        smart.finishLoadMore();
        smart.finishRefresh();
    }

    @Override
    public void getFalse(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        smart.finishLoadMore();
        smart.finishRefresh();
    }
}