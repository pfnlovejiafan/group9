package com.example.project.engine.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.project.R;
import com.example.project.base.App;
import com.example.project.mvp.ui.activity.Choose_courseActivity;
import com.example.project.mvp.ui.activity.GuideActivity;

import java.util.List;

import butterknife.BindView;

public class GuideVpAdapter extends PagerAdapter {

    private List<Integer> mPagerData;

    public GuideVpAdapter(List<Integer> pagerData) {
        this.mPagerData = pagerData;
    }

    @Override
    public int getCount() {
        return mPagerData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View root = LayoutInflater.from(App.getContent()).inflate(R.layout.item_guide_page, null);

        ImageView guidePage = root.findViewById(R.id.guide_page);
        guidePage.setImageResource(mPagerData.get(position));
        TextView mGuideSkip = root.findViewById(R.id.guide_skip);
        if (position == mPagerData.size() - 1) {

            mGuideSkip.setVisibility(View.VISIBLE);


        } else {
            mGuideSkip.setVisibility(View.GONE);

        }
        mGuideSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContent(), Choose_courseActivity.class);
                App.getContent().startActivity(intent);
            }
        });

        container.addView(root);
        return root;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
