package com.example.day3_jiaying.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day3_jiaying.R;
import com.example.day3_jiaying.bean.PicInfo;
import com.example.day3_jiaying.bean.TVBean;

import java.util.ArrayList;
import java.util.HashMap;

import cn.jzvd.JzvdStd;

public class TVAdapter extends RecyclerView.Adapter {
    private ArrayList<PicInfo.DataBeanX.DataBean> list;
    private Context context;

    public TVAdapter(ArrayList<PicInfo.DataBeanX.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tv, parent, false);
        return new ViewHolder_tv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_tv myholder= (ViewHolder_tv) holder;
        Glide.with(context)
                .load(list.get(position).getAuthor().getAvatar())
                .apply(new RequestOptions().circleCrop())
                .into(myholder.tv_touxiang);
        myholder.tv_text_yueliang.setText(list.get(position).getAuthor().getName());
        myholder.tv_title.setText(list.get(position).getFeeds_text()+"");

        myholder.jz_player.setUp((String) list.get(position).getUrl(),"文艺青年");
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource((String) list.get(position).getUrl(),new HashMap<String, String>());
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(7000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST);
        myholder.jz_player.startVideo();
        myholder.jz_player.posterImageView.setImageBitmap(frameAtTime);
        //启动音乐播放器
        myholder.jz_player.startVideo();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder_tv extends PicRevAdapter.ViewHolder {
        public View rootView;
        public ImageView tv_touxiang;
        public TextView tv_text_yueliang;
        public TextView tv_title;
        public JzvdStd jz_player;

        public ViewHolder_tv(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_touxiang = (ImageView) rootView.findViewById(R.id.tv_touxiang);
            this.tv_text_yueliang = (TextView) rootView.findViewById(R.id.tv_text_yueliang);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.jz_player = (JzvdStd) rootView.findViewById(R.id.jz_player);
        }

    }
}
