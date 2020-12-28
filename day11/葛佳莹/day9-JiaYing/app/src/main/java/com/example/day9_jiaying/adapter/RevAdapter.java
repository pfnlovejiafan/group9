package com.example.day9_jiaying.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day9_jiaying.Info.One;
import com.example.day9_jiaying.R;
import com.example.day9_jiaying.bean.BannerInfo;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RevAdapter extends RecyclerView.Adapter {
    private static final int BANNER = 1;
    private ArrayList<BannerInfo.DataBean> mBannerlist;
    private ArrayList<One> ones;
    private Context context;

    public RevAdapter(ArrayList<BannerInfo.DataBean> mBannerlist, ArrayList<One> ones, Context context) {
        this.mBannerlist = mBannerlist;
        this.ones = ones;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            View bannerview = LayoutInflater.from(context).inflate(R.layout.layout_banner, parent, false);
            return new ViewHolder_Banner(bannerview);
        }
        View twoview = LayoutInflater.from(context).inflate(R.layout.layout_two, parent, false);
        return new ViewHolder_Two(twoview);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == BANNER) {
            ViewHolder_Banner bannerholder = (ViewHolder_Banner) holder;
            bannerholder.banner_img.setImages(mBannerlist).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerInfo.DataBean bd = (BannerInfo.DataBean) path;
                    Glide.with(context).load(bd.getImagePath()).into(imageView);
                }
            }).start();
        } else if (itemViewType == 2) {
            ViewHolder_Two twoholder = (ViewHolder_Two) holder;
            twoholder.twoimg1.setImageResource(ones.get(position-1).getImgId1());
            twoholder.twoimg2.setImageResource(ones.get(position-1).getImgId2());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        }
        return 2;
    }

    @Override
    public int getItemCount() {
        return ones.size();
    }

    public static
    class ViewHolder_Banner extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner_img;

        public ViewHolder_Banner(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner_img = (Banner) rootView.findViewById(R.id.banner_img);
        }

    }

    public static
    class ViewHolder_Two extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView twoimg1;
        public ImageView twoimg2;

        public ViewHolder_Two(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.twoimg1 = (ImageView) rootView.findViewById(R.id.twoimg1);
            this.twoimg2 = (ImageView) rootView.findViewById(R.id.twoimg2);
        }

    }
}
