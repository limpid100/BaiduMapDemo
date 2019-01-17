package com.dxl.baidumapdemo;

import com.baidu.location.LocationClientOption;

/**
 * Created by dxl on 2019/1/17 20:05
 */
public final class LBSHelper {

    private static LocationClientOption sLocationClientOption = null;

    public static LocationClientOption getLocationClientOption() {
        if (sLocationClientOption == null) {
            sLocationClientOption = new LocationClientOption();
            sLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            //可选，设置定位模式，默认高精度
            //LocationMode.Hight_Accuracy：高精度；
            //LocationMode. Battery_Saving：低功耗；
            //LocationMode. Device_Sensors：仅使用设备；

            sLocationClientOption.setCoorType("bd09ll");
            //可选，设置返回经纬度坐标类型，默认GCJ02
            //GCJ02：国测局坐标；
            //BD09ll：百度经纬度坐标；
            //BD09：百度墨卡托坐标；
            //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

            sLocationClientOption.setScanSpan(1000);
            //可选，设置发起定位请求的间隔，int类型，单位ms
            //如果设置为0，则代表单次定位，即仅定位一次，默认为0
            //如果设置非0，需设置1000ms以上才有效

            sLocationClientOption.setOpenGps(true);
            //可选，设置是否使用gps，默认false
            //使用高精度和仅用设备两种定位模式的，参数必须设置为true

            sLocationClientOption.setLocationNotify(true);
            //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

            sLocationClientOption.setIgnoreKillProcess(false);
            //可选，定位SDK内部是一个service，并放到了独立进程。
            //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

            sLocationClientOption.SetIgnoreCacheException(false);
            //可选，设置是否收集Crash信息，默认收集，即参数为false

            sLocationClientOption.setWifiCacheTimeOut(5 * 60 * 1000);
            //可选，V7.2版本新增能力
            //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

            sLocationClientOption.setEnableSimulateGps(false);
            //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        }
        return sLocationClientOption;
    }
}
