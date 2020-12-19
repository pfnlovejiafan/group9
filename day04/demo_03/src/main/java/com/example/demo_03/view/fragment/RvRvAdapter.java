package com.example.demo_03.view.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo_03.R;
import com.example.demo_03.bean.DataaBean;

import java.util.ArrayList;

class RvRvAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<DataaBean.DataBean.ListBean> list;

    public RvRvAdapter(Context context, ArrayList<DataaBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_a, parent, false);
            return new ViewHolder1(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_b, parent, false);
            return new ViewHolder2(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_c, parent, false);
            return new ViewHolder3(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==1){
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.tv_a.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getFilePathList().get(0).getFilePath()).into(((ViewHolder1) holder).iv_a);
            Glide.with(context).load(list.get(position).getFilePathList().get(1).getFilePath()).into(((ViewHolder1) holder).iv_b);
            Glide.with(context).load(list.get(position).getFilePathList().get(2).getFilePath()).into(((ViewHolder1) holder).iv_c);
            holder1.tv_b.setText(list.get(position).getCreateTime());
        }
        if (itemViewType==2){
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.tv_a.setText(list.get(position).getTitle());
            holder2.tv_b.setText(list.get(position).getCreateTime());
            Glide.with(context).load(list.get(position).getFilePathList().get(0).getFilePath()).into(((ViewHolder2) holder).iv);
        }
        if (itemViewType==3){
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.tv_a.setText(list.get(position).getTitle());
            holder3.tv_b.setText(list.get(position).getCreateTime());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 1) {
            return 1;
        } else if (position % 3 == 2) {
            return 2;
        } else  {
            return 3;
        }
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_a;
        public ImageView iv_a;
        public ImageView iv_b;
        public ImageView iv_c;
        public LinearLayout ll;
        public TextView tv_b;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_a = (TextView) rootView.findViewById(R.id.tv_a);
            this.iv_a = (ImageView) rootView.findViewById(R.id.iv_a);
            this.iv_b = (ImageView) rootView.findViewById(R.id.iv_b);
            this.iv_c = (ImageView) rootView.findViewById(R.id.iv_c);
            this.ll = (LinearLayout) rootView.findViewById(R.id.ll);
            this.tv_b = (TextView) rootView.findViewById(R.id.tv_b);
        }

    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv_a;
        public TextView tv_b;
        public ConstraintLayout ll;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv_a = (TextView) rootView.findViewById(R.id.tv_a);
            this.tv_b = (TextView) rootView.findViewById(R.id.tv_b);
            this.ll = (ConstraintLayout) rootView.findViewById(R.id.ll);
        }

    }

    public static
    class ViewHolder3 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_a;
        public TextView tv_b;

        public ViewHolder3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_a = (TextView) rootView.findViewById(R.id.tv_a);
            this.tv_b = (TextView) rootView.findViewById(R.id.tv_b);
        }

    }
}
