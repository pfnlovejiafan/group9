package com.example.day5_jiaying1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.day5_jiaying1.Info.Four;
import com.example.day5_jiaying1.Info.Four_child;
import com.example.day5_jiaying1.R;

import java.util.ArrayList;

public class FourRevAdapter extends RecyclerView.Adapter {
    private ArrayList<Four_child> childs;
    private Context context;

    public FourRevAdapter(ArrayList<Four_child> childs, Context context) {
        this.childs = childs;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fouradapter, parent, false);
        return new ViewHolder_FourAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_FourAdapter viewHolder_fourAdapter = (ViewHolder_FourAdapter) holder;
        viewHolder_fourAdapter.four_text1.setText(childs.get(position).getChildtitles());
        viewHolder_fourAdapter.four_text2.setText(childs.get(position).getChilddesc());
        viewHolder_fourAdapter.four_img.setImageResource(childs.get(position).getChildImgid());

        viewHolder_fourAdapter.four_child_rv.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL, false));
        //创建数据源  绑定适配器
        ArrayList<Four> fours = new ArrayList<>();

        fours.add(new Four(R.drawable.seastar,"seaster"));
        fours.add(new Four(R.drawable.fengran,"风染"));
        fours.add(new Four(R.drawable.qingzhi,"清芷"));
        fours.add(new Four(R.drawable.hua,"花花娘子"));
        fours.add(new Four(R.drawable.xie,"斜阳未冷"));

        //设置适配器
        FourchildRevAdapter fourchildRevAdapter = new FourchildRevAdapter(fours, context);
        viewHolder_fourAdapter.four_child_rv.setAdapter(fourchildRevAdapter);
    }

    @Override
    public int getItemCount() {
        return childs.size();
    }


    public static
    class ViewHolder_FourAdapter extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView four_text1;
        public TextView four_text2;
        public ImageView four_img;
        public RecyclerView four_child_rv;

        public ViewHolder_FourAdapter(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.four_text1 = (TextView) rootView.findViewById(R.id.four_text1);
            this.four_text2 = (TextView) rootView.findViewById(R.id.four_text2);
            this.four_img = (ImageView) rootView.findViewById(R.id.four_img);
            this.four_child_rv = (RecyclerView) rootView.findViewById(R.id.four_child_rv);
        }

    }
}
