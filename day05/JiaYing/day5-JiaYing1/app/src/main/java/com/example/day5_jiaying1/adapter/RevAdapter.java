package com.example.day5_jiaying1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day5_jiaying1.Info.Five;
import com.example.day5_jiaying1.Info.Four_child;
import com.example.day5_jiaying1.Info.Preson;
import com.example.day5_jiaying1.Info.Three;
import com.example.day5_jiaying1.R;
import com.example.day5_jiaying1.bean.BannerInfo;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RevAdapter extends RecyclerView.Adapter {
    private static final int THREE = 4;
    private static final int FOUR = 5;
    private ArrayList<BannerInfo.DataBean> mBannerlist;

    private Context context;
    private TwoRevAdapter twoRevAdapter;


    public RevAdapter(ArrayList<BannerInfo.DataBean> mBannerlist, Context context) {
        this.mBannerlist = mBannerlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View bannerview = LayoutInflater.from(context).inflate(R.layout.layout_banner, parent, false);
            return new ViewHolder_Banner(bannerview);
        } else if (viewType == 2) {
            View twoview = LayoutInflater.from(context).inflate(R.layout.layout_two, parent, false);
            return new ViewHolder_Two(twoview);
        } else if (viewType == 3) {
            View threeview = LayoutInflater.from(context).inflate(R.layout.layout_three, parent, false);
            return new ViewHolder_Three(threeview);
        } else if (viewType == THREE) {
            View fourview = LayoutInflater.from(context).inflate(R.layout.layout_four, parent, false);
            return new ViewHolder_Four(fourview);
        }
        View fiveview = LayoutInflater.from(context).inflate(R.layout.layout_five, parent, false);
        return new ViewHolder_Five(fiveview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            ViewHolder_Banner bannerholder = (ViewHolder_Banner) holder;
            bannerholder.banner_img.setImages(mBannerlist).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerInfo.DataBean bd = (BannerInfo.DataBean) path;
                    Glide.with(context).load(bd.getImagePath()).into(imageView);
                }
            }).start();
        } else if (itemViewType == 2) {
            ViewHolder_Two viewHolder_two = (ViewHolder_Two) holder;
            viewHolder_two.two_rv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

            ArrayList<Preson> pers = new ArrayList<>();
            //创建适配器
            pers.add(new Preson(R.drawable.zan, "每日推荐"));
            pers.add(new Preson(R.drawable.game, "飞花令"));
            pers.add(new Preson(R.drawable.shi, "诗歌社群"));
            pers.add(new Preson(R.drawable.pai, "排行榜"));
            pers.add(new Preson(R.drawable.hui, "会员社区"));

            twoRevAdapter = new TwoRevAdapter(pers, context);
            viewHolder_two.two_rv.setAdapter(twoRevAdapter);
        } else if (itemViewType == 3) {
            ViewHolder_Three viewHolder_three = (ViewHolder_Three) holder;
            viewHolder_three.three_rv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            //创建数据源
            ArrayList<Three> threes = new ArrayList<>();
            threes.add(new Three(R.drawable.bigshi));

            //设置适配器
            ThreeRevAdapter adapter = new ThreeRevAdapter(threes, context);
            viewHolder_three.three_rv.setAdapter(adapter);
        } else if (itemViewType == THREE) {
            ViewHolder_Four viewHolder_four = (ViewHolder_Four) holder;
            viewHolder_four.four_rv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            //创建数据源
            ArrayList<Four_child> four_children = new ArrayList<>();
            four_children.add(new Four_child(R.drawable.more, "平台荣誉诗人PK", "更多"));

            //设置适配器
            FourRevAdapter fourRevAdapter = new FourRevAdapter(four_children, context);
            viewHolder_four.four_rv.setAdapter(fourRevAdapter);

        }else {
            ViewHolder_Five viewHolder_five = (ViewHolder_Five) holder;
            viewHolder_five.five_rv.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            //创建数据源  设置适配器
            ArrayList<Five> fives = new ArrayList<>();
            fives.add(new Five(R.drawable.more, "诺布尔获奖诗人", "更多"));

            //设置适配器
            FiveRevAdapter fiveRevAdapter = new FiveRevAdapter(fives, context);
            viewHolder_five.five_rv.setAdapter(fiveRevAdapter);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        } else if (position == 2) {
            return 3;
        } else if (position == 3) {
            return THREE;
        }
        return FOUR;
    }

    @Override
    public int getItemCount() {
        return mBannerlist.size();
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
        public RecyclerView two_rv;

        public ViewHolder_Two(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.two_rv = (RecyclerView) rootView.findViewById(R.id.two_rv);
        }

    }

    public static
    class ViewHolder_Three extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView three_rv;

        public ViewHolder_Three(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.three_rv = (RecyclerView) rootView.findViewById(R.id.three_rv);
        }

    }

    public static
    class ViewHolder_Four extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView four_rv;

        public ViewHolder_Four(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.four_rv = (RecyclerView) rootView.findViewById(R.id.four_rv);
        }

    }

    public static
    class ViewHolder_Five extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView five_rv;

        public ViewHolder_Five(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.five_rv = (RecyclerView) rootView.findViewById(R.id.five_rv);
        }

    }
}
