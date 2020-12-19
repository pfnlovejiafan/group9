package com.example.dome04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dome04.R;
import com.example.dome04.bean.Hot_ActivityBean;

import java.util.List;

public class HotActivityAdapter extends RecyclerView.Adapter<HotActivityAdapter.ViewHolder> {
    private final Context context;
    private final List<Hot_ActivityBean.DataBean> hot_activityList;

    public HotActivityAdapter(Context context, List<Hot_ActivityBean.DataBean> hot_activityList) {

        this.context = context;
        this.hot_activityList = hot_activityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_hotactivity_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hot_ActivityBean.DataBean dataBean = hot_activityList.get(position);

        Glide.with(context).load(dataBean.getCover()).into(holder.iv_hotactivity);

        holder.tv_title.setText(dataBean.getTitle());
        holder.tv_address.setText(dataBean.getLocation());

        String startTime = dataBean.getStartTime();
        String[] s = startTime.split(" ");

        holder.tv_time.setText(s[0]);

    }

    @Override
    public int getItemCount() {
        return hot_activityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_hotactivity;
        public TextView tv_title;
        public TextView tv_address;
        public TextView tv_time;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_hotactivity = (ImageView) rootView.findViewById(R.id.iv_hotactivity);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_address = (TextView) rootView.findViewById(R.id.tv_address);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
