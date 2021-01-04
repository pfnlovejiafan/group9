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

    private List<View> mViews;

    public GuideVpAdapter(List<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

//        View root = LayoutInflater.from(App.getContent()).inflate(R.layout.item_guide_page, null);
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
