package com.example.day9_jiaying.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day9_jiaying.Info.One;
import com.example.day9_jiaying.R;

import java.util.ArrayList;

public class OneReAdapter extends RecyclerView.Adapter {
    private ArrayList<One> oneslist;
    private Context context;
    private ImageView twoimg1;
    private ImageView twoimg2;

    public OneReAdapter(ArrayList<One> list, Context context) {
        this.oneslist = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_twochild, parent, false);
        return new ViewHolder_Twochild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_Twochild viewHolder_twochild = (ViewHolder_Twochild) holder;
        viewHolder_twochild.twoimg1.setImageResource(oneslist.get(position).getImgId1());


    }

    @Override
    public int getItemCount() {
        return oneslist.size();
    }


    public static
    class ViewHolder_Twochild extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView twoimg1;


        public ViewHolder_Twochild(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.twoimg1 = (ImageView) rootView.findViewById(R.id.twoimg1);

        }

    }
}
