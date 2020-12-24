package com.example.ceshi_06.mvp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ceshi_06.R;

public class TongActivity extends AppCompatActivity {

    private LinearLayout ll_a1;
    private LinearLayout ll_a2;
    private LinearLayout ll_a3;
    private LinearLayout ll_a4;
    private LinearLayout ll_a5;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong);
        initView();
    }

    private void initView() {
        ll_a1 = (LinearLayout) findViewById(R.id.ll_a1);
        ll_a2 = (LinearLayout) findViewById(R.id.ll_a2);
        ll_a3 = (LinearLayout) findViewById(R.id.ll_a3);
        ll_a4 = (LinearLayout) findViewById(R.id.ll_a4);
        ll_a5 = (LinearLayout) findViewById(R.id.ll_a5);
        ll = (LinearLayout) findViewById(R.id.ll);

        ll_a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TongActivity.this,XiaoB_Activity.class));
            }
        });


        ll_a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TongActivity.this,XiaoA_Activity.class));
            }
        });
    }

}
