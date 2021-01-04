package com.example.project.engine.adapter.tongpao;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.project.R;
import com.example.project.base.App;
import com.example.project.mvp.model.entity.tongpao.Topic_discussedBean;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class DiscussedAdapter extends RecyclerView.Adapter {


    private List<Topic_discussedBean.DataBean> discussedList;

    public DiscussedAdapter(List<Topic_discussedBean.DataBean> discussedList) {

        this.discussedList = discussedList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(App.getContent()).inflate(R.layout.layout_discussed_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;


        Topic_discussedBean.DataBean dataBean = discussedList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new RoundedCorners(20));
        Glide.with(App.getContent()).load(dataBean.getImageUrl()).apply(requestOptions).into(viewHolder.iv_discussed);

        viewHolder.tv_title.setText("#" + dataBean.getName() + "#");
        viewHolder.tv_count.setText(dataBean.getAttentionNum() + "人参与");

//        String string = tvTitle.getText().toString();
//        if (string.matches("#[a-zA-Z0-9]{0,}#")) {
//            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
//            Log.e(TAG, "bindData: " + string);
//        }
    }

    @Override
    public int getItemCount() {
        return discussedList.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_discussed;
        public TextView tv_title;
        public TextView tv_count;
        public TextView txt_tag;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_discussed = (ImageView) rootView.findViewById(R.id.iv_discussed);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_count = (TextView) rootView.findViewById(R.id.tv_count);
            this.txt_tag = (TextView) rootView.findViewById(R.id.txt_tag);
        }

    }
}
