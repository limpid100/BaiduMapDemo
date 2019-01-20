package com.dxl.baidumapdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.dxl.baidumapdemo.fragment.LocationResultFragment;
import com.dxl.baidumapdemo.util.LocationService;
import com.dxl.baidumapdemo.R;

/**
 * @author dxl
 * @date 2019/1/18 11:38
 */
public class LocationActivity extends AppCompatActivity {

    LocationService locationService;
    TextView mMessageView;

    LocationResultFragment fragment;
    boolean isStart = false;
    private Button mStartButton, mStopButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initLocationService();
    }

    private void initView() {
        setContentView(R.layout.activity_location);
        mMessageView = findViewById(R.id.tv_message);
        mStartButton = findViewById(R.id.btn_start);
        mStopButton = findViewById(R.id.btn_stop);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = true;
                mStartButton.setEnabled(false);
                mStopButton.setEnabled(true);
                mMessageView.setText("正在获取定位数据...");
                locationService.start();
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                mStartButton.setEnabled(true);
                mStopButton.setEnabled(false);
                locationService.stop();
            }
        });
        fragment = LocationResultFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, fragment).commit();
    }

    private void initLocationService() {
        locationService = new LocationService(getApplicationContext());
        locationService.registerListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                mMessageView.setText("定位成功：" + bdLocation.getTime());
                fragment.setBDLocation(bdLocation);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isStart) {
            locationService.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationService.stop();
    }
}
