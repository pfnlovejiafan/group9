package com.example.project.engine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.mvp.model.entity.FragmentHomeRvBean;

import java.util.ArrayList;

public class FragmentHomeRvAdapter extends RecyclerView.Adapter {
    private ArrayList<FragmentHomeRvBean> list;
    private Context context;

    public FragmentHomeRvAdapter(ArrayList<FragmentHomeRvBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder view = (ViewHolder) holder;
        FragmentHomeRvBean FragmentHomeRvBean = list.get(position);
        Glide.with(context).load(FragmentHomeRvBean.getImage1()).into(view.iv_home21);
        view.tv_home21.setText(FragmentHomeRvBean.getTitle());
        view.tv_home22.setText(FragmentHomeRvBean.getKe());
        view.tv_home23.setText(FragmentHomeRvBean.getPice());
        view.tv_home24.setText(FragmentHomeRvBean.getXue());
        view.tv_home25.setText(FragmentHomeRvBean.getGou());
        Glide.with(context).load(FragmentHomeRvBean.getIamge2()).into(view.iv_home22);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_home21;
        public TextView tv_home21;
        public TextView tv_home22;
        public TextView tv_home23;
        public ImageView iv_home22;
        public TextView tv_home24;
        public TextView tv_home25;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_home21 = (ImageView) rootView.findViewById(R.id.iv_home21);
            this.tv_home21 = (TextView) rootView.findViewById(R.id.tv_home21);
            this.tv_home22 = (TextView) rootView.findViewById(R.id.tv_home22);
            this.tv_home23 = (TextView) rootView.findViewById(R.id.tv_home23);
            this.iv_home22 = (ImageView) rootView.findViewById(R.id.iv_home22);
            this.tv_home24 = (TextView) rootView.findViewById(R.id.tv_home24);
            this.tv_home25 = (TextView) rootView.findViewById(R.id.tv_home25);
        }

    }
}
