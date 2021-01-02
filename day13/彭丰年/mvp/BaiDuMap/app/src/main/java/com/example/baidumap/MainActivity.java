package com.example.baidumap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.baidu.platform.comapi.walknavi.widget.ArCameraView;
import com.example.baidumap.fragment.BNaviFragment;
import com.example.baidumap.fragment.BaiduMapFragment;
import com.example.baidumap.fragment.MapLocationFragment;
import com.example.baidumap.fragment.MarkerClusterFragment;
import com.example.baidumap.fragment.PoiSearchFragment;
import com.example.baidumap.fragment.PoiSugSearchFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.main_tabs)
    TabLayout mainTabs;
    private Unbinder mBind;
    private List<Fragment> fragments;
    private List<String> tabs;

    private static boolean isPermissionRequested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);

        requestPermission();
        initData();

    }

    private void initData() {

        fragments = new ArrayList<>();
        tabs = new ArrayList<>();
        fragments.add(new BaiduMapFragment());
        fragments.add(new BNaviFragment());
        fragments.add(new MapLocationFragment());
        fragments.add(new PoiSearchFragment());
        fragments.add(new PoiSugSearchFragment());
        fragments.add(new MarkerClusterFragment());

        tabs.add("基本地图");
        tabs.add("导航");
        tabs.add("定位");
        tabs.add("搜索");
        tabs.add("提示搜索");
        tabs.add("点聚合");


        MainVpAdapter mainVpAdapter = new MainVpAdapter(getSupportFragmentManager());

        mainVp.setAdapter(mainVpAdapter);
        mainTabs.setupWithViewPager(mainVp);

    }

    class MainVpAdapter extends FragmentPagerAdapter {

        public MainVpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

    /**
     * Android6.0之后需要动态申请权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissionsList = new ArrayList<>();

            String[] permissions = {
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_MULTICAST_STATE


            };

            for (String perm : permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm);
                    // 进入到这里代表没有权限.
                }
            }

            if (permissionsList.isEmpty()) {
                return;
            } else {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}