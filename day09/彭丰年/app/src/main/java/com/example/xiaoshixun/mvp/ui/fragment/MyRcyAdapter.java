package com.example.xiaoshixun.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiaoshixun.R;
import com.example.xiaoshixun.base.JsonBean;
import com.example.xiaoshixun.mvp.ui.activity.SuggestPoiActivity;
import com.example.xiaoshixun.mvp.ui.activity.YiActivity;

import java.util.ArrayList;

class MyRcyAdapter extends RecyclerView.Adapter {
    private ArrayList<JsonBean> list;
    private Context context;

    public MyRcyAdapter(ArrayList<JsonBean> list, Context context) {
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
        JsonBean jsonBean = list.get(position);
        viewHolder.mTv.setText(jsonBean.getTitle());
        Glide.with(context).load(jsonBean.getImage()).into(viewHolder.mIv);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        context.startActivity(new Intent(context, SuggestPoiActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, YiActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, YiActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, YiActivity.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context, YiActivity.class));
                        break;
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView mIv;
        TextView mTv;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            this.mIv = (ImageView) view.findViewById(R.id.iv);
            this.mTv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
