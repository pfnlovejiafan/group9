package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        initView ();

    }


    private void initView() {
        rec = (RecyclerView) findViewById ( R.id.rec );
        List<ListBean> listBeans=new ArrayList<> (  );
        listBeans.add ( new ListBean ( R.drawable.zhou1 ) );
        listBeans.add ( new ListBean ( R.drawable.zhou2 ) );
        listBeans.add ( new ListBean ( R.drawable.zhou4 ) );
        listBeans.add ( new ListBean ( R.drawable.zhou3 ) );
        rec.setLayoutManager ( new StaggeredGridLayoutManager ( 2,StaggeredGridLayoutManager.VERTICAL ) );
        HomeApader homeApader = new HomeApader (this,listBeans);
        rec.setAdapter ( homeApader );


    }
}
