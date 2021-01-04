package com.example.project.engine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.mvp.model.entity.Choose_courseBean;

import java.util.List;

public class ChooseRvAdapter extends RecyclerView.Adapter {
    private List<Choose_courseBean> rvData;

    public ChooseRvAdapter(List<Choose_courseBean> rvData) {

        this.rvData = rvData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Choose_courseBean choose_courseBean = rvData.get(position);
        String title = choose_courseBean.getTitle();
        viewHolder.tv_title.setText(title);
        viewHolder.choose_item_rv.setHasFixedSize(true);
        if (viewHolder.choose_item_rv.getAdapter() == null) {
            viewHolder.choose_item_rv.setLayoutManager(new GridLayoutManager(App.getContent(), 3));
        }
        viewHolder.choose_item_rv.setAdapter(new ChooseGridAdapter(choose_courseBean.getCourse()));
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public RecyclerView choose_item_rv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.choose_item_rv = (RecyclerView) rootView.findViewById(R.id.choose_item_rv);
        }

    }
}
