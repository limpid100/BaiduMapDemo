package com.dxl.baidumapdemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.dxl.baidumapdemo.util.LocationService;

/**
 * @author dxl
 * @date 2019/1/20 13:56
 */
public class MyApplication extends Application {

    public LocationService mLocationService;

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationService = new LocationService(this);
        SDKInitializer.initialize(this);
    }
}
