package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeApader extends RecyclerView.Adapter<HomeApader.ViewHolder> {

    private final Context context;
    private final List<ListBean> listBeans;

    public HomeApader(Context context, List<ListBean> listBeans) {

        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from ( context ).inflate ( R.layout.buju2, null, false );
        return new ViewHolder ( inflate );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with ( context ).load ( listBeans.get ( position ).getImg () ).into ( holder.img1 );

    }

    @Override
    public int getItemCount() {
        return listBeans.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img1;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            img1=itemView.findViewById ( R.id.imgs );
        }
    }
}
