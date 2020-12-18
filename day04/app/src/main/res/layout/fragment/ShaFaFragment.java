package com.example.day12yue17.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day12yue17.FragmentAdapter;
import com.example.day12yue17.R;
import com.example.day12yue17.neifragment.ImgFragment;
import com.example.day12yue17.neifragment.ShiPingFragment;
import com.example.day12yue17.neifragment.WenBenFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ShaFaFragment extends Fragment {

    private View view;
    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shafa_item, null);
        initView();
        return view;
    }

    private void initView() {
        tab = view.findViewById(R.id.tab);
        vp = view.findViewById(R.id.vp);

        list = new ArrayList<>();
        list.add(new ImgFragment());
        list.add(new ShiPingFragment());
        list.add(new WenBenFragment());

        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), list);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("图片");
        tab.getTabAt(1).setText("视频");
        tab.getTabAt(2).setText("文本");

    }
}
