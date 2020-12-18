package com.example.day4_jiaying.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day4_jiaying.R;
import com.example.day4_jiaying.adapter.FindVpAdapter;
import com.example.day4_jiaying.fragment2.PaiFragment;
import com.example.day4_jiaying.fragment2.PaoFragment;
import com.example.day4_jiaying.fragment2.SheFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class FindFragment extends Fragment {
    private TabLayout find_tab;
    private ViewPager find_vip;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private ArrayList<Integer> iconsss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        find_tab=view.findViewById(R.id.find_tab);
        find_vip=view.findViewById(R.id.find_vip);

        fragments = new ArrayList<>();
        fragments.add(new PaoFragment());
        fragments.add(new SheFragment());
        fragments.add(new PaiFragment());


        titles = new ArrayList<>();
        titles.add("袍子");
        titles.add("社团");
        titles.add("排行榜");

        FindVpAdapter adapter = new FindVpAdapter(getChildFragmentManager(), fragments, titles);
        find_vip.setAdapter(adapter);
        find_tab.setupWithViewPager(find_vip);

        iconsss = new ArrayList<>();
        iconsss.add(R.drawable.icon_music);
        iconsss.add(R.drawable.icon_music);
        iconsss.add(R.drawable.icon_music);

        for (int i = 0; i < fragments.size(); i++) {
            find_tab.getTabAt(i).setCustomView(getitemViews(i));
        }
    }

    private View getitemViews(int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_tabitem_find, null);
        TextView tabitem_text = view.findViewById(R.id.tabitem_text_find);
        ImageView tabitem_img = view.findViewById(R.id.tabitem_img_find);
        tabitem_img.setImageResource(iconsss.get(position));
        tabitem_text.setText(titles.get(position));
        return view;
    }
}