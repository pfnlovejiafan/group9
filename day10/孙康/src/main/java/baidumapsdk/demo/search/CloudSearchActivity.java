package baidumapsdk.demo.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.cloud.BoundSearchInfo;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudRgcInfo;
import com.baidu.mapapi.cloud.CloudRgcResult;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchInfo;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.LocalSearchInfo;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.example.mybaidumap.R;


/**
 * 此demo演示了云检索功能
 */
public class CloudSearchActivity extends AppCompatActivity implements CloudListener {

    private static final String LTAG = CloudSearchActivity.class.getSimpleName();
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private CloudManager mCloudManager;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_lbssearch);
        // 初始化跟注册监听分开，确保资源正确释放，防止内存泄露，请使用新接口
        mCloudManager = CloudManager.getInstance();
        mCloudManager.init();
        mCloudManager.registerListener(CloudSearchActivity.this);

        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        findViewById(R.id.regionSearch).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LocalSearchInfo info = new LocalSearchInfo();
                        info.ak = "B266f735e43ab207ec152deff44fec8b";
                        info.geoTableId = 31869;
                        info.tags = "";
                        info.q = "天安门";
                        info.region = "北京市";
                        mCloudManager.localSearch(info);
                    }
                });
        findViewById(R.id.nearbySearch).setOnClickListener(
                new OnClickListener() {
                    public void onClick(View v) {
                        NearbySearchInfo info = new NearbySearchInfo();
                        info.ak = "D9ace96891048231e8777291cda45ca0";
                        info.geoTableId = 32038;
                        info.radius = 30000;
                        info.location = "116.403689,39.914957";
                        mCloudManager.nearbySearch(info);
                    }
                });

        findViewById(R.id.boundsSearch).setOnClickListener(
                new OnClickListener() {
                    public void onClick(View v) {
                        BoundSearchInfo info = new BoundSearchInfo();
                        info.ak = "B266f735e43ab207ec152deff44fec8b";
                        info.geoTableId = 31869;
                        info.q = "天安门";
                        info.bound = "116.401663,39.913961;116.406529,39.917396";
                        mCloudManager.boundSearch(info);
                    }
                });
        findViewById(R.id.detailsSearch).setOnClickListener(
                new OnClickListener() {
                    public void onClick(View v) {
                        DetailSearchInfo info = new DetailSearchInfo();
                        info.ak = "B266f735e43ab207ec152deff44fec8b";
                        info.geoTableId = 31869;
                        //考虑到数据的溢出，请使用podId代替uid。
                        info.poiId = "18622266";
                        mCloudManager.detailSearch(info);
                    }
                });
        findViewById(R.id.cloudRgc).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CloudSearchActivity.this, "请开发者使用自己AK下的geo_table", Toast.LENGTH_SHORT).show();
                        CloudRgcInfo info = new CloudRgcInfo();
                        //注：请开发者使用自己AK下的geo_table
                        info.geoTableId = 145801;
                        info.location = "40.047699,116.313718";
                        mCloudManager.rgcSearch(info);
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放监听者
        mCloudManager.unregisterListener();
        mCloudManager.destroy();
        mCloudManager= null;
        mMapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    public void onGetDetailSearchResult(DetailSearchResult result, int error) {
        if (result != null) {
            if (result.poiInfo != null) {
                Toast.makeText(CloudSearchActivity.this, result.poiInfo.title, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CloudSearchActivity.this, "status:" + result.status, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onGetSearchResult(CloudSearchResult result, int error) {
        if (result != null && result.poiList != null && result.poiList.size() > 0) {
            Log.d(LTAG, "onGetSearchResult, result length: " + result.poiList.size());
            mBaiduMap.clear();
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
            LatLng latng;
            Builder builder = new Builder();
            for (CloudPoiInfo info : result.poiList) {
                latng = new LatLng(info.latitude, info.longitude);
                OverlayOptions overlayOptions = new MarkerOptions().icon(bitmap).position(latng);
                mBaiduMap.addOverlay(overlayOptions);
                builder.include(latng);
            }
            LatLngBounds bounds = builder.build();
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(bounds);
            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }
    }

    @Override
    public void onGetCloudRgcResult(CloudRgcResult result, int error) {
        if (result != null && error == 0) {
            if (result.status == 0) {
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
                mBaiduMap.clear();
                LatLng latng;
                Builder builder = new Builder();
                if (result.customPois != null && result.customPois.size() > 0) {
                    for (int i = 0; i < result.customPois.size(); i++) {
                        CloudPoiInfo info = result.customPois.get(i);
                        latng = new LatLng(info.latitude, info.longitude);
                        OverlayOptions overlayOptions = new MarkerOptions().icon(bitmap).position(latng);
                        mBaiduMap.addOverlay(overlayOptions);
                        TextOptions TextOptions = new TextOptions().text(info.title).position(latng).bgColor(0xffff0000).fontSize(30);
                        mBaiduMap.addOverlay(TextOptions);
                        builder.include(latng);
                    }
                }

                try {
                    OverlayOptions overlay = new MarkerOptions().icon(bitmap).position(result.location);
                    Marker marker = (Marker) mBaiduMap.addOverlay(overlay);

                    builder.include(result.location);
                    TextOptions textOptions = new TextOptions().text(result.customLocationDescription).position(result
                            .location).bgColor(0xffff0000).fontSize(30);
                    mBaiduMap.addOverlay(textOptions);

                    LatLngBounds bounds = builder.build();
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(bounds);

                    mBaiduMap.animateMapStatus(mapStatusUpdate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(CloudSearchActivity.this, "status:" + result.status, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

