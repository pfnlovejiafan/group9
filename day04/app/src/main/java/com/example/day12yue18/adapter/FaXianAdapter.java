package com.example.day12yue18.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day12yue18.FaXianBean;
import com.example.day12yue18.R;

import java.util.ArrayList;

public class FaXianAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<FaXianBean.DataBean> list;
    private ArrayList<FaXianBean.DataBean> list1;

    public FaXianAdapter(Context context, ArrayList<FaXianBean.DataBean> list, ArrayList<FaXianBean.DataBean> list1) {
        this.context = context;
        this.list = list;
        this.list1 = list1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = View.inflate(context, R.layout.paozi_item, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            View view = View.inflate(context, R.layout.lie_item, null);
            ViewHolder1 holder1 = new ViewHolder1(view);
            return holder1;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        FaXianBean.DataBean dataBean = list.get(position);
        if (type == 1) {

            ViewHolder holder1 = (ViewHolder) holder;
            holder1.desc1.setText(dataBean.getNickName());
            holder1.desc2.setText(dataBean.getNickName());
            holder1.desc3.setText(dataBean.getNickName());
            Glide.with(context).load(dataBean.getHeadUrl()).into(holder1.image1);
            Glide.with(context).load(dataBean.getHeadUrl()).into(holder1.image2);
            Glide.with(context).load(dataBean.getHeadUrl()).into(holder1.image3);

        }else {
            ViewHolder1 holder2 = (ViewHolder1) holder;
            holder2.desc11.setText(dataBean.getNickName());
            holder2.desc22.setText(dataBean.getNickName());
            Glide.with(context).load(dataBean.getHeadUrl()).into(holder2.image11);
            Glide.with(context).load(dataBean.getHeadUrl()).into(holder2.image22);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView image1;
        public TextView desc1;
        public ImageView image2;
        public TextView desc2;
        public ImageView image3;
        public TextView desc3;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.image1 = (ImageView) rootView.findViewById(R.id.image1);
            this.desc1 = (TextView) rootView.findViewById(R.id.desc1);
            this.image2 = (ImageView) rootView.findViewById(R.id.image2);
            this.desc2 = (TextView) rootView.findViewById(R.id.desc2);
            this.image3 = (ImageView) rootView.findViewById(R.id.image3);
            this.desc3 = (TextView) rootView.findViewById(R.id.desc3);
        }

    }

    public static
    class ViewHolder1 extends ViewHolder {
        public View rootView;
        public ImageView image11;
        public TextView desc11;
        public ImageView image22;
        public TextView desc22;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.image11 = (ImageView) rootView.findViewById(R.id.image11);
            this.desc11 = (TextView) rootView.findViewById(R.id.desc11);
            this.image22 = (ImageView) rootView.findViewById(R.id.image22);
            this.desc22 = (TextView) rootView.findViewById(R.id.desc22);
        }

    }
}
