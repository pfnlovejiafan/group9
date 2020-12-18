package com.example.day3_jiaying.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day3_jiaying.R;
import com.example.day3_jiaying.bean.PicInfo;

import java.util.ArrayList;

public class PicRevAdapter extends RecyclerView.Adapter {
    private  ArrayList<PicInfo.DataBeanX.DataBean> authorBeans;
    private  Context context;

    public PicRevAdapter(ArrayList<PicInfo.DataBeanX.DataBean> authorBeans, Context context) {
        this.authorBeans = authorBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pic = LayoutInflater.from(context).inflate(R.layout.layout_pic, parent, false);
        return new ViewHolder(pic);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myholder= (ViewHolder) holder;
       Glide.with(context).load(authorBeans.get(position).getAuthor().getAvatar())
              .apply(new RequestOptions().circleCrop())
              .into(myholder.touxiang);
       myholder.text_yueliang.setText(authorBeans.get(position).getAuthor().getName());
       myholder.title.setText(authorBeans.get(position).getFeeds_text());
        Glide.with(context).load(authorBeans.get(position).getCover()).into(myholder.bigimg);
        myholder.last_text.setText(authorBeans.get(position).getActivityText());
    }

    @Override
    public int getItemCount() {
        return authorBeans.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView touxiang;
        public TextView text_yueliang;
        public TextView title;
        public ImageView bigimg;
        public TextView last_text;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.touxiang = (ImageView) rootView.findViewById(R.id.touxiang);
            this.text_yueliang = (TextView) rootView.findViewById(R.id.text_yueliang);
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.bigimg = (ImageView) rootView.findViewById(R.id.bigimg);
            this.last_text = (TextView) rootView.findViewById(R.id.last_text);
        }

    }
}
