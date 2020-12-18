package com.example.day042;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day042.bean.JsonBean;

import java.util.ArrayList;

class MyRcyAdapter extends RecyclerView.Adapter {
    private ArrayList<JsonBean.DataBean> list;
    private Context context;

    public MyRcyAdapter(ArrayList<JsonBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rcy, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        JsonBean.DataBean dataBean = list.get(position);
        viewHolder.tv.setText(dataBean.getTitle());
        Glide.with(context).load(dataBean.getCover()).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
}
