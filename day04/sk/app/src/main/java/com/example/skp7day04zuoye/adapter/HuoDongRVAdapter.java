package com.example.skp7day04zuoye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skp7day04zuoye.R;
import com.example.skp7day04zuoye.model.bean.HuodongBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HuoDongRVAdapter extends RecyclerView.Adapter {
    private ArrayList<HuodongBean.DataBean> list;
    private Context context;

    public HuoDongRVAdapter(ArrayList<HuodongBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.huodong_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        HuodongBean.DataBean bean = list.get(position);
        Glide.with(context).load(bean.getCover()).into(viewHolder.iv);
        viewHolder.tvItemTitle.setText(bean.getTitle());
        viewHolder.tvDidian.setText(bean.getLocation());
        viewHolder.tvRiqi.setText(bean.getApplyCutOffTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_item_title)
        TextView tvItemTitle;
        @BindView(R.id.tv_didian)
        TextView tvDidian;
        @BindView(R.id.tv_riqi)
        TextView tvRiqi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
