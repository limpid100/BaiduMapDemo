package com.dxl.baidumapdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.dxl.baidumapdemo.R;

/**
 * 基本地图使用
 *
 * @author dxl
 */
public class BasicMapActivity extends AppCompatActivity {

    MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_map);
        mMapView = findViewById(R.id.mapview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //实现生命周期
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
