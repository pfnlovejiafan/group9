package com.example.ceshi_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RvD_Adapter extends RecyclerView.Adapter<RvD_Adapter.ViewHolder> {
    private Context context;

    public RvD_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_d, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position==0){
            holder.iv.setImageResource(R.drawable.e1);
            holder.iv_a.setImageResource(R.drawable.e3);
            holder.iv_b.setImageResource(R.drawable.ic_e1);
        }
        if (position==1){
            holder.iv.setImageResource(R.drawable.e2);
            holder.iv_a.setImageResource(R.drawable.e3);
            holder.iv_b.setImageResource(R.drawable.ic_e2);
            holder.tv_1.setText("寒露");
            holder.tv_2.setText("流年的清寒");
            holder.tv_3.setText("晶莹透剔成珠玉");
            holder.tv_4.setText("牵念，夜的深情");
            holder.tv_5.setText("剑胆琴心");
            holder.tv_6.setText("已收藏");
            holder.iv_a.setMaxWidth(161);
            holder.iv_a.setMinimumWidth(161);
            holder.iv_a.setMaxHeight(175);
            holder.iv_a.setMinimumHeight(175);
        }
        if(position==2){
            holder.iv.setImageResource(R.drawable.e2);
            holder.iv_a.setImageResource(R.drawable.e3);
            holder.iv_b.setImageResource(R.drawable.ic_e2);
            holder.tv_1.setText("或曰无题");
            holder.tv_2.setText("我愿是正在进行的青...");
            holder.tv_3.setText("是拂过你长发的那缕...");
            holder.tv_4.setText("在榴火簇拥有葱花的...");
            holder.tv_6.setText("已收藏");
        }
        if (position==3){
            holder.iv.setImageResource(R.drawable.e1);
            holder.iv_a.setImageResource(R.drawable.e3);
            holder.iv_b.setImageResource(R.drawable.ic_e1);
            holder.tv_6.setText("已收藏");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv;
        public TextView tv_1;
        public TextView tv_2;
        public TextView tv_3;
        public TextView tv_4;
        public ImageView iv_a;
        public TextView tv_5;
        public ImageView iv_b;
        public TextView tv_6;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
            this.tv = (TextView) itemView.findViewById(R.id.tv);
            this.tv_1 = (TextView) itemView.findViewById(R.id.tv_1);
            this.tv_2 = (TextView) itemView.findViewById(R.id.tv_2);
            this.tv_3 = (TextView) itemView.findViewById(R.id.tv_3);
            this.tv_4 = (TextView) itemView.findViewById(R.id.tv_4);
            this.iv_a = (ImageView) itemView.findViewById(R.id.iv_a);
            this.tv_5 = (TextView) itemView.findViewById(R.id.tv_5);
            this.iv_b = (ImageView) itemView.findViewById(R.id.iv_b);
            this.tv_6 = (TextView) itemView.findViewById(R.id.tv_6);
        }
    }

}
