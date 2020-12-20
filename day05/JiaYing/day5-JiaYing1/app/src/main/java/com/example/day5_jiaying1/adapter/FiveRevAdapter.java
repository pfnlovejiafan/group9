package com.example.day5_jiaying1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5_jiaying1.Info.Five;
import com.example.day5_jiaying1.R;

import java.util.ArrayList;

public class FiveRevAdapter extends RecyclerView.Adapter {
    private ArrayList<Five> fives;
    private Context context;

    public FiveRevAdapter(ArrayList<Five> fives, Context context) {
        this.fives = fives;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fiveadapter, parent, false);
        return new ViewHolder_FiveAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_FiveAdapter viewHolder_fiveAdapter= (ViewHolder_FiveAdapter) holder;
        viewHolder_fiveAdapter.five_text1.setText(fives.get(position).getFivedesc());
        viewHolder_fiveAdapter.five_text2.setText(fives.get(position).getFivetitles());
        viewHolder_fiveAdapter.five_img.setImageResource(fives.get(position).getFiveImgid());

    }

    @Override
    public int getItemCount() {
        return fives.size();
    }

    public static
    class ViewHolder_FiveAdapter extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView five_text1;
        public TextView five_text2;
        public ImageView five_img;
        public RecyclerView five_child_rv;

        public ViewHolder_FiveAdapter(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.five_text1 = (TextView) rootView.findViewById(R.id.five_text1);
            this.five_text2 = (TextView) rootView.findViewById(R.id.five_text2);
            this.five_img = (ImageView) rootView.findViewById(R.id.five_img);
            this.five_child_rv = (RecyclerView) rootView.findViewById(R.id.five_child_rv);
        }

    }
}
