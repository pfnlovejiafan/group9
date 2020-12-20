package com.example.day05zy;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private ImageView iv3;
    private ImageView iv2;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    private ImageView iv7;
    private ImageView iv8;
    private ImageView iv9;
    private ImageView iv10;
    private ImageView iv11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv6 = (ImageView) findViewById(R.id.iv6);
        iv7 = (ImageView) findViewById(R.id.iv7);
        Glide.with(this).load(R.drawable.vs).into(iv3);
        Glide.with(this).load(R.drawable.tou1).into(iv);
        Glide.with(this).load(R.drawable.tou2).into(iv2);
        Glide.with(this).load(R.drawable.tou4).into(iv4);
        Glide.with(this).load(R.drawable.a).into(iv5);
        Glide.with(this).load(R.drawable.b).into(iv6);
        Glide.with(this).load(R.drawable.b).into(iv7);

        iv8 = (ImageView) findViewById(R.id.iv8);
        iv9 = (ImageView) findViewById(R.id.iv9);
        iv10 = (ImageView) findViewById(R.id.iv10);
        iv11 = (ImageView) findViewById(R.id.iv11);

        Glide.with(this).load(R.drawable.tou4).into(iv8);
        Glide.with(this).load(R.drawable.a).into(iv9);
        Glide.with(this).load(R.drawable.b).into(iv10);
        Glide.with(this).load(R.drawable.b).into(iv11);
    }
}
