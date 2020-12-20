package com.example.day5_jiaying1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5_jiaying1.Info.Four;
import com.example.day5_jiaying1.Info.Four_child;
import com.example.day5_jiaying1.R;

import java.util.ArrayList;

public class FourchildRevAdapter extends RecyclerView.Adapter {
    private ArrayList<Four> four_children;
    private Context context;

    public FourchildRevAdapter(ArrayList<Four> four_children, Context context) {
        this.four_children = four_children;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_fourchlid, parent, false);

        return new ViewHolder_Fourchild(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_Fourchild viewHolder_fourchild= (ViewHolder_Fourchild) holder;
        viewHolder_fourchild.fouriv1.setImageResource(four_children.get(position).getFourImgID());
        viewHolder_fourchild.fouriv2.setImageResource(four_children.get(position).getFourImgID());
        viewHolder_fourchild.fourtv5.setText(four_children.get(position).getFourtitle());
        viewHolder_fourchild.fourtv55.setText(four_children.get(position).getFourtitle());
    }

    @Override
    public int getItemCount() {
        return four_children.size();
    }

    public static
    class ViewHolder_Fourchild extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView fouriv1;
        public TextView fourtv5;
        public LinearLayout fourchild_main;
        public ImageView fouriv2;
        public TextView fourtv55;

        public ViewHolder_Fourchild(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.fouriv1 = (ImageView) rootView.findViewById(R.id.fouriv1);
            this.fourtv5 = (TextView) rootView.findViewById(R.id.fourtv5);
            this.fourchild_main = (LinearLayout) rootView.findViewById(R.id.fourchild_main);
            this.fouriv2 = (ImageView) rootView.findViewById(R.id.fouriv2);
            this.fourtv55 = (TextView) rootView.findViewById(R.id.fourtv55);
        }

    }
}
