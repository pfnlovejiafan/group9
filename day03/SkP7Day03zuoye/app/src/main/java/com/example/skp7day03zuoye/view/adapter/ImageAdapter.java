package com.example.skp7day03zuoye.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.skp7day03zuoye.R;
import com.example.skp7day03zuoye.model.bean.ImagesBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter {
    private ArrayList<ImagesBean.DataBeanX.DataBean> list;
    private Context context;

    public ImageAdapter(ArrayList<ImagesBean.DataBeanX.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.images_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ImagesBean.DataBeanX.DataBean dataBean = list.get(position);
        ImagesBean.DataBeanX.DataBean.AuthorBean author = dataBean.getAuthor();
        ImagesBean.DataBeanX.DataBean.UgcBean ugc = dataBean.getUgc();
        Glide.with(context).load(author.getAvatar()).apply(new RequestOptions().circleCrop()).into(viewHolder.ivAvatar);
        viewHolder.tvName.setText(author.getName());
        viewHolder.tvFeedsText.setText(dataBean.getFeeds_text());
        Glide.with(context).load(dataBean.getCover()).into(viewHolder.ivCover);
        viewHolder.tvActivityText.setText(dataBean.getActivityText());
        viewHolder.tvLikeCount.setText(ugc.getLikeCount()+"");
        viewHolder.tvDissCount.setText(ugc.getShareCount()+"");
        viewHolder.tvShareCount.setText(ugc.getShareCount()+"");
        viewHolder.tvCommentCount.setText(ugc.getCommentCount()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_feeds_text)
        TextView tvFeedsText;
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_activityText)
        TextView tvActivityText;
        @BindView(R.id.iv_like)
        ImageView ivLike;
        @BindView(R.id.tv_like_count)
        TextView tvLikeCount;
        @BindView(R.id.iv_diss)
        ImageView ivDiss;
        @BindView(R.id.tv_diss_count)
        TextView tvDissCount;
        @BindView(R.id.iv_comment)
        ImageView ivComment;
        @BindView(R.id.tv_comment_count)
        TextView tvCommentCount;
        @BindView(R.id.share)
        ImageView share;
        @BindView(R.id.tv_share_count)
        TextView tvShareCount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
