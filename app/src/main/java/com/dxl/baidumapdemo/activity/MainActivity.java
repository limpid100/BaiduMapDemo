package com.dxl.baidumapdemo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dxl.baidumapdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dxl
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 定位的权限
     */
    private final String[] REQUEST_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private List<String> mPermissionList = new ArrayList<>();
    public static final int PERMISSION_REQUEST_CODE = 1001;
    private Class<?> mClazz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        findViewById(R.id.basic_location).setOnClickListener(this);
        findViewById(R.id.auto_notify).setOnClickListener(this);
        findViewById(R.id.notify_location).setOnClickListener(this);
        findViewById(R.id.basic_map).setOnClickListener(this);
    }

    private void requestPermission() {
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(this, mPermissionList.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        } else {
            startActivity();
        }
    }

    /**
     * 检测是否所有权限都允许了
     *
     * @return
     */
    private boolean checkPermission() {
        mPermissionList.clear();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : REQUEST_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission);
            }
        }
        return mPermissionList.size() == 0;
    }

    private void startActivity() {
        if (mClazz != null) {
            startActivity(new Intent(MainActivity.this, mClazz));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allGranted = true;
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                final String permission = permissions[i];
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    //提示，并在此请求权限
                    AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示");
                    builder.setMessage("定位需要此权限！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (shouldShowRequestPermissionRationale(permission)) {
                                        requestPermission();
                                    } else {
                                        //跳转到设置
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intent.setData(Uri.parse("package:" + getPackageName()));
                                        startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                                    }

                                }
                            }).show();
                    break;
                }
            }
            if (allGranted) {
                startActivity();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (checkPermission()) {
                startActivity();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_location:
                mClazz = LocationActivity.class;
                break;
            case R.id.auto_notify:
                mClazz = AutoNotifyActivity.class;
                break;
            case R.id.notify_location:
                mClazz = NotifyActivity.class;
                break;
            case R.id.basic_map:
                mClazz = BasicMapActivity.class;
            default:
        }
        requestPermission();
    }
}
