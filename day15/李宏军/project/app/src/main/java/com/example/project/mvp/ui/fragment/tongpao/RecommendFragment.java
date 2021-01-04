package com.example.project.mvp.ui.fragment.tongpao;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.base.App;
import com.example.project.base.BaseFragment;
import com.example.project.base.BasePresenter;
import com.example.project.engine.adapter.tongpao.DiscussedAdapter;
import com.example.project.mvp.model.entity.tongpao.BannerBean;
import com.example.project.mvp.model.entity.tongpao.Topic_discussedBean;
import com.example.project.mvp.presenter.tongpao.TpRecPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecommendFragment extends BaseFragment {

    private static final String TAG = "TAG";

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerview_talk)
    RecyclerView recyclerviewTalk;

    private List<Topic_discussedBean.DataBean> discussedList;
    DiscussedAdapter discussedAdapter;


    @Override
    protected BasePresenter createPresenter() {
        return new TpRecPresenter(this);
    }

    @Override
    protected void init() {
        discussedList = new ArrayList<>();
        discussedAdapter = new DiscussedAdapter(discussedList);
        recyclerviewTalk.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerviewTalk.setAdapter(discussedAdapter);
    }

    @Override
    protected void initData() {
        getPresenter().start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_t_p_recommend;
    }

    @Override
    public void onSucceed(Object o) {
        if (o instanceof BannerBean) {
            BannerBean bannerBean = (BannerBean) o;
            List<BannerBean.DataBean.ListBean> list = bannerBean.getData().getList();
            banner.setImages(list).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean.ListBean listBean = (BannerBean.DataBean.ListBean) path;

                    Glide.with(context).load(listBean.getBanner()).into(imageView);

                }
            }).start();
        } else if (o instanceof Topic_discussedBean) {
            Topic_discussedBean discussedBean = (Topic_discussedBean) o;
            discussedList.clear();
            discussedList.addAll(discussedBean.getData());
            discussedAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFiled(String errorMsg) {
        Log.e(TAG, "TAG:" + App.getStr(R.string.request_error) + "：" + errorMsg);
        Toast.makeText(App.getContent(), App.getStr(R.string.request_error), Toast.LENGTH_SHORT).show();
    }
}
