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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.dome04.R;
import com.example.dome04.bean.RodeBean;

import java.util.List;

public class RodeAdapter extends RecyclerView.Adapter<RodeAdapter.ViewHolder> {
    private final Context context;
    private final List<RodeBean.DataBean> rodeList;

    public RodeAdapter(Context context, List<RodeBean.DataBean> rodeList) {

        this.context = context;
        this.rodeList = rodeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_rode, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(rodeList!=null&&rodeList.size()>0){
            RodeBean.DataBean dataBean = rodeList.get(position);
            holder.tv_city.setText(dataBean.getCity());
            holder.tv_nickName.setText(dataBean.getNickName());
            Glide.with(context).load(dataBean.getHeadUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.iv_headerUrl);
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_headerUrl;
        public TextView tv_nickName;
        public TextView tv_city;
        public TextView tv_focus;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_headerUrl = (ImageView) rootView.findViewById(R.id.iv_headerUrl);
            this.tv_nickName = (TextView) rootView.findViewById(R.id.tv_nickName);
            this.tv_city = (TextView) rootView.findViewById(R.id.tv_city);
            this.tv_focus = (TextView) rootView.findViewById(R.id.tv_focus);
        }

    }
}
