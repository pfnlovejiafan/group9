package com.example.day1_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_start = (Button) findViewById(R.id.btn_start);

        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                //使用类名.调用方法executeTask(里面可以直接new Runnable(执行任务的方法))
                ThreadPoolManager.getmManager().executeTask(runnable);

                //调用封装成单例ok的方法
                ApiService apiService =RetrofitMannager.getmManager().getmApiService();
                break;
        }
    }

    //或者写成匿名内部类的写法
    private Runnable runnable =new Runnable() {
        @Override
        public void run() {
            //执行耗时操作

        }
    };


    //如果进行销毁的话直接重写方法  调用单例方法即可
    @Override
    protected void onDestroy() {
        ThreadPoolManager.getmManager().removeTask(runnable);
        super.onDestroy();
    }
}