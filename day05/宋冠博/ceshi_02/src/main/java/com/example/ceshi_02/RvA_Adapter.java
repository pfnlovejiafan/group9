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

class RvA_Adapter extends RecyclerView.Adapter<RvA_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> strings;
    private ArrayList<Integer> list;

    public RvA_Adapter(Context context, ArrayList<String> strings, ArrayList<Integer> list) {
        this.context = context;
        this.strings = strings;
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
        holder.iv.setImageResource(list.get(position));
        holder.tv.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
            this.tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
