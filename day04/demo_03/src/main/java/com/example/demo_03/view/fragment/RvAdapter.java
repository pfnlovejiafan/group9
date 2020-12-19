package com.example.demo_03.view.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo_03.R;
import com.example.demo_03.bean.ReMenBean;

import java.util.ArrayList;

class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ReMenBean.DataBean> list;

    public RvAdapter(Context context, ArrayList<ReMenBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(holder.iv);
        holder.tv_a.setText(list.get(position).getTitle());
        holder.tv_b.setText(list.get(position).getLocation());
        holder.tv_c.setText(list.get(position).getStartTime());

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv_a;
        public TextView tv_b;
        public TextView tv_c;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv_a = (TextView) rootView.findViewById(R.id.tv_a);
            this.tv_b = (TextView) rootView.findViewById(R.id.tv_b);
            this.tv_c = (TextView) rootView.findViewById(R.id.tv_c);
        }

    }
}
