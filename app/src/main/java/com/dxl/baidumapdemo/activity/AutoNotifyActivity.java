package com.dxl.baidumapdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.dxl.baidumapdemo.MyApplication;
import com.dxl.baidumapdemo.R;
import com.dxl.baidumapdemo.fragment.LocationResultFragment;
import com.dxl.baidumapdemo.util.LocationService;

/**
 * 自定义回调
 *
 * @author dxl
 */
public class AutoNotifyActivity extends AppCompatActivity {

    EditText mEtTimeInterval, mEtDistanceInterval;
    TextView mTvStartLocation, mTvMessage;
    AppCompatSpinner mSpinner;
    private static final String[] SENSIVITYS = {"高灵敏", "中灵敏", "低灵敏"};
    private static final int[] SENSIVITYS_INT = {LocationClientOption.LOC_SENSITIVITY_HIGHT,
            LocationClientOption.LOC_SENSITIVITY_MIDDLE,
            LocationClientOption.LOC_SENSITIVITY_LOW};
    private LocationService mLocationService;
    private LocationClientOption mLocationClientOption;
    private LocationResultFragment mLocationResultFragment;
    private String lastLocationTime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initLocationService();
    }

    private void initLocationService() {
        mLocationService = ((MyApplication) getApplication()).mLocationService;
        mLocationClientOption = mLocationService.getDefaultLocationClientOption();
        mLocationService.registerListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                mLocationResultFragment.setBDLocation(bdLocation);
                String currentLocationTime = bdLocation.getTime();
                mTvMessage.setText("定位成功：定位时间：" + currentLocationTime + "上次定位时间：" + lastLocationTime);
                lastLocationTime = currentLocationTime;
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_auto_notify);
        mEtTimeInterval = findViewById(R.id.et_time_interval);
        mEtDistanceInterval = findViewById(R.id.et_distance_interval);
        mTvStartLocation = findViewById(R.id.tv_start_location);
        mSpinner = findViewById(R.id.spinner);
        mTvMessage = findViewById(R.id.tv_message);
        mLocationResultFragment = LocationResultFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, mLocationResultFragment).commit();

        mSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SENSIVITYS));
        mSpinner.setSelection(0);
        mTvStartLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvMessage.setText("正在定位...");
                //参数:
                //minTimeInterval - 最短定位时间间隔，单位毫秒，最小值0，开发者可以在设置希望的位置回调最短时间间隔
                //minDistance - 最短定位距离间隔，单位米，最小值0，开发者可以设置希望的位置回调距离间隔
                //locSensitivity - 定位变化敏感程度,LOC_SENSITIVITY_HIGHT,LOC_SENSITIVITY_MIDDLE,LOC_SENSITIVITY_LOW
                mLocationClientOption.setOpenAutoNotifyMode(Integer.valueOf(mEtTimeInterval.getText().toString()) * 1000,
                        Integer.valueOf(mEtDistanceInterval.getText().toString()),
                        SENSIVITYS_INT[mSpinner.getSelectedItemPosition()]);
                mLocationService.setLocationClientOption(mLocationClientOption);
                mLocationService.start();
            }
        });
    }
}
