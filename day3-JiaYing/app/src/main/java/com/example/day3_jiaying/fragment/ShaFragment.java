package com.example.day3_jiaying.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day3_jiaying.R;
import com.example.day3_jiaying.adapter.ShaVpAdapter;
import com.example.day3_jiaying.fragment1.PicFragment;
import com.example.day3_jiaying.fragment1.TextFragment;
import com.example.day3_jiaying.fragment1.TvFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ShaFragment extends Fragment {


    private TabLayout sha_tab;
    private ViewPager sha_vip_first;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sha, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initeiew(getView());
    }

    private void initeiew(View view) {
        sha_tab=view.findViewById(R.id.sha_tab);
        sha_vip_first=view.findViewById(R.id.sha_vip_first);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PicFragment());
        fragments.add(new TvFragment());
        fragments.add(new TextFragment());

        ArrayList<String> titles = new ArrayList<>();
        titles.add("图片");
        titles.add("视频");
        titles.add("文本");

        ShaVpAdapter adapter = new ShaVpAdapter(getChildFragmentManager(), fragments, titles);
        sha_vip_first.setAdapter(adapter);
        sha_tab.setupWithViewPager(sha_vip_first);
    }
}