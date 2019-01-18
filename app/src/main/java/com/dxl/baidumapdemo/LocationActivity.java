package com.dxl.baidumapdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

/**
 * @author dxl
 * @date 2019/1/18 11:38
 */
public class LocationActivity extends AppCompatActivity {

    TextView tv_main;
    LocationService locationService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv_main = findViewById(R.id.tv_main);
        initLocationService();
    }

    private void initLocationService() {
        locationService = new LocationService(getApplicationContext());
        locationService.registerListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                tv_main.setText(bdLocation.getLatitude() + "\n" + bdLocation.getLongitude() + "\n" + bdLocation.getAddress().address);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationService.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationService.stop();
    }
}
