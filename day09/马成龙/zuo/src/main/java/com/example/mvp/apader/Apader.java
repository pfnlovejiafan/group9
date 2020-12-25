package com.example.mvp.apader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvp.R;
import com.example.mvp.bean.HuoBean;
import com.example.mvp.bean.ListBean;
import com.example.mvp.mvp.ui.activity.DiTuActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Apader extends RecyclerView.Adapter {
    private static final int TYPE_NOE = 1;
    private static final int TYPE_TWO = 2;
    private final Context context;
    private final List<ListBean> listBeans;
    List<HuoBean.DataBean> list=new ArrayList<> (  );

    public Apader(Context context, List<ListBean> listBeans) {

        this.context = context;
        this.listBeans = listBeans;
    }
    public void addlist( List<HuoBean.DataBean> list){
        this.list.addAll ( list );

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_NOE;
        } else {
            return TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NOE) {
            View inflate = LayoutInflater.from ( context ).inflate ( R.layout.buju1, null, false );
            return new Text1ViewHolder ( inflate );
        } else {
            View inflate = LayoutInflater.from ( context ).inflate ( R.layout.buju2, null, false );
            return new Text2ViewHolder ( inflate );
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType ( position )){
            case TYPE_NOE:
                Text1ViewHolder holder1= (Text1ViewHolder) holder;
                Glide.with ( context ).load ( R.drawable.yeu1 ).into ( holder1.img1 );
                Glide.with ( context ).load ( R.drawable.yue2 ).into ( holder1.img2 );
                Glide.with ( context ).load ( R.drawable.yue3 ).into ( holder1.img3 );
                Glide.with ( context ).load ( R.drawable.yue4 ).into ( holder1.img4 );
                Glide.with ( context ).load ( R.drawable.yue5 ).into ( holder1.img5 );
                holder1.img4.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        context.startActivity ( new Intent ( context, DiTuActivity.class ) );
                    }
                } );
                break;
            case TYPE_TWO:
                Text2ViewHolder holder2= (Text2ViewHolder) holder;
                holder2.rec1.setLayoutManager ( new LinearLayoutManager ( context,LinearLayoutManager.HORIZONTAL,true ) );
                MyApader myApader = new MyApader ( context, list );
                holder2.rec1.setAdapter ( myApader );
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }



    static
    class Text1ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.ts1)
        TextView ts1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.ts2)
        TextView ts2;
        @BindView(R.id.img3)
        ImageView img3;
        @BindView(R.id.ts3)
        TextView ts3;
        @BindView(R.id.img4)
        ImageView img4;
        @BindView(R.id.ts4)
        TextView ts4;
        @BindView(R.id.img5)
        ImageView img5;
        @BindView(R.id.ts5)
        TextView ts5;

        Text1ViewHolder(View view) {
            super ( view );
            ButterKnife.bind ( this, view );
        }
    }

    class Text2ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rec1)
        RecyclerView rec1;

        Text2ViewHolder(View view) {
            super (view);
            ButterKnife.bind ( this, view );
        }
    }
}
