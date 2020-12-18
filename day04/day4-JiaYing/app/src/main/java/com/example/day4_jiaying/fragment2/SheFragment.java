package com.example.day4_jiaying.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day4_jiaying.R;


public class SheFragment extends Fragment {
    private RecyclerView she_rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_she, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initeView(getView());
    }

    private void initeView(View view) {
        she_rv=view.findViewById(R.id.she_rv);
        she_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //创建数据源

    }
}