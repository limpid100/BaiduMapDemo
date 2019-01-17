package com.dxl.baidumapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;

/**
 * @author dxl
 */
public class MainActivity extends AppCompatActivity {

    private LocationClient mLocationClient;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textview);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.setLocOption(LBSHelper.getLocationClientOption());
        mLocationClient.registerLocationListener(new MyLocationListener());
    }


    class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            textview.setText(bdLocation.getLatitude() + "\n" + bdLocation.getLongitude() + "\n" + bdLocation.getLocType());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationClient.start();
    }
}
