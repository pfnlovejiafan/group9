package com.example.xiaoshixun.mvp.ui.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.example.xiaoshixun.R;

import java.util.List;

public class HomeActRlAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<SuggestionResult.SuggestionInfo> allSuggestions;

    public HomeActRlAdapter(Context context, List<SuggestionResult.SuggestionInfo> allSuggestions) {
        this.context = context;
        this.allSuggestions = allSuggestions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_suggest_poi_item, parent, false);
        return new SuggestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SuggestionResult.SuggestionInfo suggestionInfo = allSuggestions.get(position);

        String city = suggestionInfo.city;
        String key = suggestionInfo.key;
        String district=  suggestionInfo.district;

        Log.e("TAG",city+"========"+key+"========"+district);
        SuggestViewHolder suggestViewHolder = (SuggestViewHolder) holder;
        suggestViewHolder.home_suggest_tv.setText(key);
       suggestViewHolder.home_suggest_tv_area.setText(city+district);
    }


    @Override
    public int getItemCount() {
        return allSuggestions.size();
    }


    public static class SuggestViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView home_suggest_tv;
        public TextView home_suggest_tv_area;

        public SuggestViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.home_suggest_tv = (TextView) rootView.findViewById(R.id.home_suggest_tv);
            this.home_suggest_tv_area = (TextView) rootView.findViewById(R.id.home_suggest_tv_area);
        }

    }
}