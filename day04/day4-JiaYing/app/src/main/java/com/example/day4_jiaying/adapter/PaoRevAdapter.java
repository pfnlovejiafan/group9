package com.example.day4_jiaying.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day4_jiaying.R;
import com.example.day4_jiaying.bean.PaoInfo;

import java.util.ArrayList;

public class PaoRevAdapter extends RecyclerView.Adapter {
    private ArrayList<PaoInfo.DataBean> list;
    private Context context;

    public PaoRevAdapter(ArrayList<PaoInfo.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View paoview = LayoutInflater.from(context).inflate(R.layout.layout_pao, parent, false);
        return new ViewHolder_Pao(paoview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_Pao viewHolder_pao= (ViewHolder_Pao) holder;
        Glide.with(context).load(list.get(position).getCover()).into(viewHolder_pao.pao_img);
        viewHolder_pao.pao_title.setText(list.get(position).getTitle());
        viewHolder_pao.applyCutOffTime_text.setText(list.get(position).getApplyCutOffTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder_Pao extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView pao_img;
        public TextView pao_title;
        public TextView applyCutOffTime_text;

        public ViewHolder_Pao(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.pao_img = (ImageView) rootView.findViewById(R.id.pao_img);
            this.pao_title = (TextView) rootView.findViewById(R.id.pao_title);
            this.applyCutOffTime_text = (TextView) rootView.findViewById(R.id.applyCutOffTime_text);
        }

    }
}
