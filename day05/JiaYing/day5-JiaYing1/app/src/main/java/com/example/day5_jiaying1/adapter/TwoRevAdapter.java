package com.example.day5_jiaying1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5_jiaying1.Info.Preson;
import com.example.day5_jiaying1.R;

import java.util.ArrayList;

public class TwoRevAdapter extends RecyclerView.Adapter {
    private ArrayList<Preson> list;
    private Context context;

    public TwoRevAdapter(ArrayList<Preson> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_twoadapter, parent, false);
        return new ViewHolder_TwoAdapter(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_TwoAdapter twoAdapter= (ViewHolder_TwoAdapter) holder;
        twoAdapter.two_img1.setImageResource(list.get(position).getImgId());
        twoAdapter.two_text1.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder_TwoAdapter extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView two_img1;
        public TextView two_text1;

        public ViewHolder_TwoAdapter(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.two_img1 = (ImageView) rootView.findViewById(R.id.two_img1);
            this.two_text1 = (TextView) rootView.findViewById(R.id.two_text1);
        }

    }
}
