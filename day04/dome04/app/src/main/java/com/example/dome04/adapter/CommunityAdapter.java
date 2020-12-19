package com.example.dome04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dome04.R;
import com.example.dome04.bean.CommunityBean;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private final Context context;
    private final List<CommunityBean.DataBean.ListBean> communityList;

    public CommunityAdapter(Context context, List<CommunityBean.DataBean.ListBean> communityList) {
        this.context = context;
        this.communityList = communityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_community, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (communityList != null && communityList.size() > 0) {

            CommunityBean.DataBean.ListBean listBean = communityList.get(position);

            holder.tv_fullName.setText(listBean.getFullName());
            holder.tv_countUser.setText("成员："+listBean.getCountUser());
            holder.tv_countActivity.setText("活动："+listBean.getCountActivity());
            holder.tv_note.setText(listBean.getNote());
            Glide.with(context).load(listBean.getLogo()).into(holder.iv_logo);
        }

    }

    @Override
    public int getItemCount() {
        return communityList.size();
    }


    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_logo;
        public TextView tv_fullName;
        public TextView tv_countUser;
        public TextView tv_countActivity;
        public TextView tv_note;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_logo = (ImageView) rootView.findViewById(R.id.iv_logo);
            this.tv_fullName = (TextView) rootView.findViewById(R.id.tv_fullName);
            this.tv_countUser = (TextView) rootView.findViewById(R.id.tv_countUser);
            this.tv_countActivity = (TextView) rootView.findViewById(R.id.tv_countActivity);
            this.tv_note = (TextView) rootView.findViewById(R.id.tv_note);
        }

    }
}
