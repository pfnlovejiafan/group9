package com.example.shixun2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class BlankFragment extends Fragment {

    private Banner bann;
    private ArrayList<Integer> image;
    private TextView tv;
    private TextView tv2;
    private RecyclerView rcy;
    private ArrayList<Stu> list;
    private ArrayList<Stu> list2;
    private MyRcyAdapter myRcyAdapter;
    private TextView tv3;
    private RecyclerView rcy2;
    private MyRcyAdapter2 myRcyAdapter2;
    private TextView tv4;
    private ViewPager vp;
    private ArrayList<View> imageVps;
    private MyVpAdapter myVpAdapter;
    private TextView tv5;
    private ImageView iv;
    private ImageView iv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {


        bann = (Banner) inflate.findViewById(R.id.bann);
        image = new ArrayList<>();
        image.add(R.drawable.ph1);
        image.add(R.drawable.ph2);
        image.add(R.drawable.ph3);
        image.add(R.drawable.ph5);
        bann.setImages(image)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(getActivity()).load(path).into(imageView);
                    }
                }).start();
        tv = (TextView) inflate.findViewById(R.id.tv);
        tv2 = (TextView) inflate.findViewById(R.id.tv2);
        rcy = (RecyclerView) inflate.findViewById(R.id.rcy);
        LinearLayoutManager ms = new LinearLayoutManager(getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy.setLayoutManager(ms);
        list = new ArrayList<Stu>();
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        myRcyAdapter = new MyRcyAdapter(list, getContext());
        rcy.setAdapter(myRcyAdapter);


        tv3 = (TextView) inflate.findViewById(R.id.tv3);
        rcy2 = (RecyclerView) inflate.findViewById(R.id.rcy2);
        LinearLayoutManager ms1 = new LinearLayoutManager(getContext());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy2.setLayoutManager(ms1);
        list = new ArrayList<Stu>();
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        list.add(new Stu("你好", R.drawable.ph1));
        myRcyAdapter2 = new MyRcyAdapter2(getContext(), list);
        rcy2.setAdapter(myRcyAdapter2);

        tv4 = (TextView) inflate.findViewById(R.id.tv4);
        vp = (ViewPager) inflate.findViewById(R.id.vp);
        imageVps = new ArrayList<View>();
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.vp1, null);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.vp2, null);
        View view3 = LayoutInflater.from(getContext()).inflate(R.layout.vp3, null);
        View view4 = LayoutInflater.from(getContext()).inflate(R.layout.vp4, null);
        imageVps.add(view1);
        imageVps.add(view2);
        imageVps.add(view3);
        imageVps.add(view4);
        myVpAdapter = new MyVpAdapter(imageVps);
        vp.setAdapter(myVpAdapter);
        tv5 = (TextView) inflate.findViewById(R.id.tv5);
        iv = (ImageView) inflate.findViewById(R.id.iv);
    }

    private void initData() {

    }
}
