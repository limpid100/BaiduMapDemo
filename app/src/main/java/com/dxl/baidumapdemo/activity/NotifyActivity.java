package com.dxl.baidumapdemo.activity;

import android.app.Service;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.dxl.baidumapdemo.R;
import com.dxl.baidumapdemo.util.LocationService;

/**
 * 位置提醒
 * 定位SDK支持位置提醒功能，位置提醒最多提醒3次，3次过后将不再提醒。假如需要再次提醒、或者要修改提醒点坐标，都可通过函数SetNotifyLocation()来实现。
 *
 * @author dxl
 */
public class NotifyActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup mGroup;
    private TextView mSetText, mResultText;
    private static final double[][] LOCATIONS = new double[][]{{35.110615, 118.367091}, {0, 0}, {0, 0}};
    private Vibrator mVibrator;
    LocationClient mLocationClient;
    private int selectLocation = 0;

    private BDNotifyListener mNotifyListener = new BDNotifyListener() {
        @Override
        public void onNotify(BDLocation bdLocation, float v) {
            Toast.makeText(NotifyActivity.this, "位置提醒", Toast.LENGTH_SHORT).show();
            mResultText.setText(bdLocation.getLatitude() + "，" + bdLocation.getLongitude() + "\n" + bdLocation.getTime());
            mVibrator.vibrate(1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initLocationService();

    }

    private void initLocationService() {
        //1.初始化LocationClient类
        mLocationClient = new LocationClient(this);
    }


    private void initView() {
        setContentView(R.layout.activity_notify);
        mSetText = findViewById(R.id.tv_set);
        mResultText = findViewById(R.id.tv_result);
        mGroup = findViewById(R.id.radioGroup);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < mGroup.getChildCount(); i++) {
                    if (((RadioButton) mGroup.getChildAt(i)).isChecked()) {
                        selectLocation = i;
                        mSetText.setText("设置：" + LOCATIONS[i][0] + "，" + LOCATIONS[i][1] + "，3000");
                        break;
                    }
                }
            }
        });
        ((RadioButton) mGroup.getChildAt(0)).setChecked(true);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        findViewById(R.id.start).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (((Button) v).getText().toString().equals("开始")) {
            //2.注册监听函数
            mLocationClient.registerNotify(mNotifyListener);
            //3.设置位置提醒
            //只能提醒三次,如果需要多次提醒，需要多次调用该方法
            mNotifyListener.SetNotifyLocation(LOCATIONS[selectLocation][0], LOCATIONS[selectLocation][1], 3000, mLocationClient.getLocOption().getCoorType());
            //4启动
            mLocationClient.start();
            ((Button) v).setText("结束");
        }else {
            if (mNotifyListener != null) {
                mLocationClient.removeNotifyEvent(mNotifyListener);
                mLocationClient.stop();
                mResultText.setText("");
                ((Button) v).setText("开始");
            }
        }

    }
}
