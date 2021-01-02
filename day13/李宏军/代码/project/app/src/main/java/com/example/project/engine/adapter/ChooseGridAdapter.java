package com.example.project.engine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.base.App;

import java.util.List;

public class ChooseGridAdapter extends RecyclerView.Adapter {
    private List<String> course;

    public ChooseGridAdapter(List<String> course) {

        this.course = course;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(App.getContent()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.btn_course.setText(course.get(position));
    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public Button btn_course;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.btn_course = (Button) rootView.findViewById(R.id.btn_course);
        }

    }


}
