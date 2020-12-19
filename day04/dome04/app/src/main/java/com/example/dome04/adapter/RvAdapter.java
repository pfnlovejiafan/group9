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
import com.bumptech.glide.request.RequestOptions;
import com.example.dome04.R;
import com.example.dome04.bean.HotBean;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<HotBean.DataBean.ListBean> list;
    private String type;
    private static final int THREE_IMAGE = 0;
    private static final int IMAGE_TEXT = 1;
    private static final int TEXT_TYPE = 2;

    @Override
    public int getItemViewType(int position) {
        if (!"4".equals(type) && position % 3 == 0) {
            return THREE_IMAGE;
        } else if (!"4".equals(type) && position % 3 == 1) {
            return IMAGE_TEXT;
        } else if ("4".equals(type) && position == 1) {
            return IMAGE_TEXT;
        } else {
            return TEXT_TYPE;
        }
    }

    public RvAdapter(Context context, List<HotBean.DataBean.ListBean> list, String type) {

        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == THREE_IMAGE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_three_image, parent, false);
            return new ThreeImageViewHolder(view);
        } else if (viewType == IMAGE_TEXT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image_text, parent, false);
            return new ImageTextViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
            return new TextTypeViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HotBean.DataBean.ListBean listBean = list.get(position);
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case THREE_IMAGE:
                ThreeImageViewHolder threeImageViewHolder = (ThreeImageViewHolder) holder;

                List<HotBean.DataBean.ListBean.FilePathListBean> filePathList = listBean.getFilePathList();
                List<ImageView> ivs = new ArrayList<>();
                ivs.add(threeImageViewHolder.iv_url1);
                ivs.add(threeImageViewHolder.iv_url2);
                ivs.add(threeImageViewHolder.iv_url3);
                for (int i = 0; i < filePathList.size(); i++) {
                    HotBean.DataBean.ListBean.FilePathListBean filePathListBean = filePathList.get(i);
                    String filePath = filePathListBean.getFilePath();

                    RequestOptions requestOptions = new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher);
                    Glide.with(context).load(filePath).apply(requestOptions).into(ivs.get(i));

                }
                threeImageViewHolder.tv_createTime.setText(listBean.getCreateTime());
                threeImageViewHolder.tv_title.setText(listBean.getTitle());

                break;
            case IMAGE_TEXT:
                ImageTextViewHolder imageTextViewHolder = (ImageTextViewHolder) holder;

                List<HotBean.DataBean.ListBean.FilePathListBean> filePathList2 = listBean.getFilePathList();


                HotBean.DataBean.ListBean.FilePathListBean filePathListBean = filePathList2.get(0);
                String filePath = filePathListBean.getFilePath();
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher);
                Glide.with(context).load(filePath).apply(requestOptions).into(imageTextViewHolder.iv_url);


                imageTextViewHolder.tv_createTime.setText(listBean.getCreateTime());
                imageTextViewHolder.tv_title.setText(listBean.getTitle());

                break;
            case TEXT_TYPE:

                TextTypeViewHolder textTypeViewHolder = (TextTypeViewHolder) holder;

                textTypeViewHolder.tv_createTime.setText(listBean.getCreateTime());
                textTypeViewHolder.tv_title.setText(listBean.getTitle());

                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ThreeImageViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public ImageView iv_url1;
        public ImageView iv_url2;
        public ImageView iv_url3;
        public TextView tv_createTime;

        public ThreeImageViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.iv_url1 = (ImageView) rootView.findViewById(R.id.iv_url1);
            this.iv_url2 = (ImageView) rootView.findViewById(R.id.iv_url2);
            this.iv_url3 = (ImageView) rootView.findViewById(R.id.iv_url3);
            this.tv_createTime = (TextView) rootView.findViewById(R.id.tv_createTime);
        }

    }

    public static
    class ImageTextViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public ImageView iv_url;
        public TextView tv_createTime;

        public ImageTextViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.iv_url = (ImageView) rootView.findViewById(R.id.iv_url);
            this.tv_createTime = (TextView) rootView.findViewById(R.id.tv_createTime);
        }

    }

    public static
    class TextTypeViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_createTime;

        public TextTypeViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_createTime = (TextView) rootView.findViewById(R.id.tv_createTime);
        }

    }
}
