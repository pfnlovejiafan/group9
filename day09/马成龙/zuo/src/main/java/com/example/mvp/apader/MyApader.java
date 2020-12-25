package com.example.mvp.apader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvp.R;
import com.example.mvp.bean.HuoBean;

import java.util.List;

public class MyApader extends RecyclerView.Adapter<MyApader.ViewHolder> {
    private final Context context;
    private final List<HuoBean.DataBean> list;

    public MyApader(Context context, List<HuoBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from ( context ).inflate ( R.layout.buju3, null, false );
        return new ViewHolder ( inflate );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with ( context ).load ( list.get ( position ).getCover () ).into ( holder.imgs );
        holder.tss.setText ( list.get ( position ).getTitle () );

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgs;
        private TextView tss;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            imgs=itemView.findViewById ( R.id.imgs );
            tss=itemView.findViewById ( R.id.tss );
        }
    }
}
