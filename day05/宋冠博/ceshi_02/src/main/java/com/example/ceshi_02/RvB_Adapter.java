package com.example.ceshi_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RvB_Adapter extends RecyclerView.Adapter<RvB_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> strings;

    public RvB_Adapter(Context context, ArrayList<String> strings, ArrayList<Integer> list) {
        this.context = context;
        this.strings = strings;
        this.list = list;
    }

    private ArrayList<Integer> list;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_a, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.iv.setImageResource(list.get(position));
        holder.tv_c.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv_a;
        public TextView tv_b;
        public TextView tv_c;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
            this.tv_a = (TextView) itemView.findViewById(R.id.tv_a);
            this.tv_b = (TextView) itemView.findViewById(R.id.tv_b);
            this.tv_c = (TextView) itemView.findViewById(R.id.tv_c);

        }
    }

}
