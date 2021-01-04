package com.example.project.mvp.ui.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.base.App;
import com.example.project.base.BaseFragment;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.FragmentHomeRvAdapter;
import com.example.project.mvp.model.entity.FragmentHomeRvBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.fragment_home_banner)
    Banner fragmentHomeBanner;
    @BindView(R.id.fragment_home_rv)
    RecyclerView fragmentHomeRv;



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {


        List<String> mBannerList = new ArrayList<>();
        mBannerList.add("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");
        mBannerList.add("https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png");
        mBannerList.add("https://wanandroid.com/blogimgs/8690f5f9-733a-476a-8ad2-2468d043c2d4.png");
        fragmentHomeBanner.setImages(mBannerList).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                String url = (String) path;
                Glide.with(context).load(url).into(imageView);
            }
        }).start();

        fragmentHomeRv.setLayoutManager(new LinearLayoutManager(App.getContent(),RecyclerView.VERTICAL,false));
        FragmentHomeRvAdapter fragmentHomeRvAdapter = new FragmentHomeRvAdapter(getList(),App.getContent());
        fragmentHomeRv.setAdapter(fragmentHomeRvAdapter);

    }

    private ArrayList<FragmentHomeRvBean> getList() {
        ArrayList<FragmentHomeRvBean> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new FragmentHomeRvBean(R.drawable.fengmian,
                    "股权投资基金基础知识（至尊VIP密训班）",
                    "备考导学，基础学习，系统精讲",
                    "6800 6800",
                    R.drawable.xiaofang,
                    "免费试学",
                    "5132人已购买"));
        }

        return list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
