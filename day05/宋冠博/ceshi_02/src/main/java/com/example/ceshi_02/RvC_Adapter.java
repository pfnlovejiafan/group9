package com.example.ceshi_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RvC_Adapter extends RecyclerView.Adapter<RvC_Adapter.ViewHolder> {
    private Context context;

    public RvC_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_b, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (position){
            case 0:
                holder.tv_1.setText("青春是飞鸟");
                holder.tv_2.setText("日子每天都鲜亮");
                holder.tv_3.setText("每一个梦都有一千对翅膀");
                holder.tv_4.setText("_《你是青春里最先照亮我的那束光》逸族网");
                break;
            case 1:
                holder.tv_1.setText("可我不想播了");
                holder.tv_2.setText("对树");
                holder.tv_3.setText("那是寒露");
                holder.tv_4.setText("_《写给秋的一首》逸族网");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_1;
        public TextView tv_2;
        public TextView tv_3;
        public TextView tv_4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_1 = (TextView) itemView.findViewById(R.id.tv_1);
            this.tv_2 = (TextView) itemView.findViewById(R.id.tv_2);
            this.tv_3 = (TextView) itemView.findViewById(R.id.tv_3);
            this.tv_4 = (TextView) itemView.findViewById(R.id.tv_4);
        }
    }

}
