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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.dome04.R;
import com.example.dome04.bean.RodeNearBean;

import java.util.List;

public class RodeNearAdapter extends RecyclerView.Adapter<RodeNearAdapter.ViewHolder> {
    private final Context context;
    private final List<RodeNearBean.DataBean.ListBean> rodeNearList;

    public RodeNearAdapter(Context context, List<RodeNearBean.DataBean.ListBean> rodeNearList) {

        this.context = context;
        this.rodeNearList = rodeNearList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_rode_near, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (rodeNearList != null && rodeNearList.size() > 0) {
            RodeNearBean.DataBean.ListBean listBean = rodeNearList.get(position);
            holder.tv_signature.setText(listBean.getSignature());
            holder.tv_nickName.setText(listBean.getNickName());
            holder.tv_scope.setText(listBean.getScope() + "米");
            Glide.with(context).load(listBean.getHeadUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.iv_headerUrl);
        }

    }

    @Override
    public int getItemCount() {
        return rodeNearList.size();
    }


    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_headerUrl;
        public TextView tv_nickName;
        public TextView tv_signature;
        public TextView tv_scope;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_headerUrl = (ImageView) rootView.findViewById(R.id.iv_headerUrl);
            this.tv_nickName = (TextView) rootView.findViewById(R.id.tv_nickName);
            this.tv_signature = (TextView) rootView.findViewById(R.id.tv_signature);
            this.tv_scope = (TextView) rootView.findViewById(R.id.tv_scope);
        }

    }
}
