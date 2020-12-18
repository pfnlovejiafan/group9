package com.example.skp7day03zuoye.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.skp7day03zuoye.R;
import com.example.skp7day03zuoye.view.fragment.sofaFragment.SofaImagesFragment;
import com.example.skp7day03zuoye.view.fragment.sofaFragment.SofaTextFragment;
import com.example.skp7day03zuoye.view.fragment.sofaFragment.SofaVideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SofaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    private String mParam1;
    private String mParam2;
    private View view;
    private SofaImagesFragment sofaImagesFragment;
    private SofaVideoFragment sofaVideoFragment;
    private SofaTextFragment sofaTextFragment;

    public SofaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SofaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SofaFragment newInstance(String param1, String param2) {
        SofaFragment fragment = new SofaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sha_fa, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        sofaImagesFragment = new SofaImagesFragment();
        sofaVideoFragment = new SofaVideoFragment();
        sofaTextFragment = new SofaTextFragment();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(sofaImagesFragment);
        fragments.add(sofaVideoFragment);
        fragments.add(sofaTextFragment);
        VPAdapter adapter = new VPAdapter(getChildFragmentManager(),fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setText("图片");
        tab.getTabAt(1).setText("视频");
        tab.getTabAt(2).setText("文字");

    }


}