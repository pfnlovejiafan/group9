package com.example.day5_jiaying1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5_jiaying1.Info.Three;
import com.example.day5_jiaying1.R;

import java.util.ArrayList;

public class ThreeRevAdapter extends RecyclerView.Adapter {
    private ArrayList<Three> threes;
    private Context context;

    public ThreeRevAdapter(ArrayList<Three> threes, Context context) {
        this.threes = threes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_threeadapter, parent, false);
        return new ViewHolder_ThreeAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_ThreeAdapter viewHolder_threeAdapter= (ViewHolder_ThreeAdapter) holder;
        viewHolder_threeAdapter.three_text1.setText("诗歌群PK");
        viewHolder_threeAdapter.three_text2.setText("08-01");
        viewHolder_threeAdapter.three_text3.setText("日晚");
        viewHolder_threeAdapter.three_text4.setText("8:00");
        viewHolder_threeAdapter.three_text5.setText("开始");
        viewHolder_threeAdapter.three_img.setImageResource(threes.get(position).getThreeimgId());
    }

    @Override
    public int getItemCount() {
        return threes.size();
    }

    public static
    class ViewHolder_ThreeAdapter extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView three_text1;
        public TextView three_text2;
        public TextView three_text3;
        public TextView three_text4;
        public TextView three_text5;
        public ImageView three_img;

        public ViewHolder_ThreeAdapter(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.three_text1 = (TextView) rootView.findViewById(R.id.three_text1);
            this.three_text2 = (TextView) rootView.findViewById(R.id.three_text2);
            this.three_text3 = (TextView) rootView.findViewById(R.id.three_text3);
            this.three_text4 = (TextView) rootView.findViewById(R.id.three_text4);
            this.three_text5 = (TextView) rootView.findViewById(R.id.three_text5);
            this.three_img = (ImageView) rootView.findViewById(R.id.three_img);
        }

    }
}
