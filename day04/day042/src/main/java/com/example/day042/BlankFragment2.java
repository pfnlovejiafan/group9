package com.example.day042;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
=======

>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD
import com.example.day042.bean.SheBean;
import com.example.day042.mvp.IView;
import com.example.day042.mvp.RelletP;

import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710

/**
 * A simple {@link Fragment} subclass.
 */
<<<<<<< HEAD
public class BlankFragment2 extends Fragment implements IView {


    private RelletP relletP;
    private RecyclerView rcy;
    private ArrayList<SheBean.DataBean.ListBean> list;
    private MyRcyAdapter3 myRcyAdapter3;
=======
public class BlankFragment2 extends Fragment {

>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        View inflate = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        relletP = new RelletP(this);
        relletP.startShe();
    }

    private void initView(View inflate) {
        rcy = inflate.findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        myRcyAdapter3 = new MyRcyAdapter3(list, getContext());
        rcy.setAdapter(myRcyAdapter3);
    }

    @Override
    public void showOk(Object object) {
        if (object instanceof SheBean){
            SheBean sheBean= (SheBean) object;
            List<SheBean.DataBean.ListBean> list1 = sheBean.getData().getList();
            list.addAll(list1);
            myRcyAdapter3.notifyDataSetChanged();
        }
    }

    @Override
    public void showNo(String string) {
        Log.e("showNo: ", string);
    }
=======
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
}
