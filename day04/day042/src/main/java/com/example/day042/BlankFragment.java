package com.example.day042;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
=======
>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD
import com.example.day042.bean.PaoBean;
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
public class BlankFragment extends Fragment implements IView {


    private RecyclerView rcy;
    private ArrayList<PaoBean.DataBean> list;
    private MyRcyAdapter2 myRcyAdapter2;

=======
public class BlankFragment extends Fragment {


>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        RelletP relletP = new RelletP(this);
        relletP.JsonPao();
    }

    private void initView(View inflate) {
        rcy = inflate.findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        myRcyAdapter2 = new MyRcyAdapter2(list, getContext());
        rcy.setAdapter(myRcyAdapter2);
    }

    @Override
    public void showOk(Object object) {
        if (object instanceof PaoBean) {
            PaoBean paoBean= (PaoBean) object;
            List<PaoBean.DataBean> data = paoBean.getData();
            list.addAll(data);
            myRcyAdapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void showNo(String string) {

    }
=======
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

>>>>>>> 2df4231c1df942233ab1adfa1db3391e2e6ec710
}
