package com.dxl.baidumapdemo;

import android.util.SparseArray;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dxl
 * @date 2019/1/19 11:24
 */
public class Util {

    public static List<LocationBean> getLocationBeans(BDLocation bdLocation) {
        List<LocationBean> beanList = new ArrayList<>();

        DecimalFormat format = new DecimalFormat("#.####");
        beanList.add(new LocationBean("getAdCode()", bdLocation.getAdCode() + "", "获取城市adcode"));
        Address address = bdLocation.getAddress();
        beanList.add(new LocationBean("getAddress() ", "adcode:" + address.adcode + " \naddress:" + address.address
                + "\ncity:" + address.city + "\ncitycode:" + address.cityCode + " \ncountry:" + address.country + "\ncountrycode:" +
                address.countryCode + "\ndistrict:" + address.district + "\nprovince:" + address.province + "\nstreet:" + address.street
                + "\nstreetnumber:" + address.streetNumber, "地址信息"));
        beanList.add(new LocationBean("getAddrStr()", bdLocation.getAddrStr() + "", "获取详细地址信息"));
        beanList.add(new LocationBean("getAltitude()", format.format(bdLocation.getAltitude()) + "", "获取高度信息，目前只有是GPS定位结果时或者设置LocationClientOption.setIsNeedAltitude(true)时才有效，单位米"));
        beanList.add(new LocationBean("getBuildingID()", bdLocation.getBuildingID() + "", "获取buildingid信息，目前只在百度支持室内定位的地方有返回，默认null"));
        beanList.add(new LocationBean("getBuildingName()", bdLocation.getBuildingName() + "", "获取buildingid信息，目前只在百度支持室内定位的地方有返回，默认null"));
        beanList.add(new LocationBean("getCity()", bdLocation.getCity() + "", "获取城市"));
        beanList.add(new LocationBean("getCityCode()", bdLocation.getCityCode() + "", "获取城市编码"));
        beanList.add(new LocationBean("getCoorType()", bdLocation.getCoorType() + "", "获取所用坐标系，以locationClientOption里设定的坐标系为准(wgs84,gcj02,bd09,bd09ll)"));
        beanList.add(new LocationBean("getCountry()", bdLocation.getCountry() + "", "获取国家"));
        beanList.add(new LocationBean("getCountryCode()", bdLocation.getCountryCode() + "", "获取国家编码"));
        beanList.add(new LocationBean("getDirection()", bdLocation.getDirection() + "", "gps定位结果时，行进的方向，单位度"));
        beanList.add(new LocationBean("getDistrict()", bdLocation.getDistrict() + "", "获取区/县信息"));
        beanList.add(new LocationBean("getFloor()", bdLocation.getFloor() + "", "获取楼层信息，目前只在百度支持室内定位的地方有返回，默认null"));
        int gpsAccuracyStatus = bdLocation.getGpsAccuracyStatus();
        String status = "";
        switch (gpsAccuracyStatus) {
            case BDLocation.GPS_ACCURACY_GOOD:
                status = "GPS_ACCURACY_GOOD";
                break;
            case BDLocation.GPS_ACCURACY_MID:
                status = "GPS_ACCURACY_MID";
                break;
            case BDLocation.GPS_ACCURACY_BAD:
                status = "GPS_ACCURACY_BAD";
                break;
            case BDLocation.GPS_ACCURACY_UNKNOWN:
            default:
                status = "GPS_ACCURACY_UNKNOWN";
                break;
        }
        beanList.add(new LocationBean("getGpsAccuracyStatus()", status + "", "如果是GPS位置，获得当前由百度自有算法判断的GPS质量, #GPS_ACCURACY_GOOD , #GPS_ACCURACY_MID, #GPS_ACCURACY_UNKNOWN"));
        beanList.add(new LocationBean("getGpsCheckStatus()", bdLocation.getGpsCheckStatus() + "", ""));

        beanList.add(new LocationBean("getIndoorLocationSource()", bdLocation.getIndoorLocationSource() + "", "返回支持的室内定位类型 #INDOOR_LOCATION_SOURCE_WIFI, #INDOOR_LOCATION_SOURCE_BLUETOOTH, #INDOOR_LOCATION_SOURCE_MAGNETIC, #INDOOR_LOCATION_SOURCE_SMALLCELLSTATION, #INDOOR_LOCATION_SOURCE_UNKNOWN"));
        beanList.add(new LocationBean("getIndoorLocationSurpport()", bdLocation.getIndoorLocationSurpport() + "", "返回是否支持室内定位，,#INDOOR_LOCATION_NEARBY_SURPPORT_TRUE,#INDOOR_LOCATION_SURPPORT_FALSE,#INDOOR_LOCATION_SURPPORT_UNKNOWN"));
        beanList.add(new LocationBean("getIndoorLocationSurpportBuidlingID()", bdLocation.getIndoorLocationSurpportBuidlingID() + "", "返回支持室内定位的buildingid"));
        beanList.add(new LocationBean("getIndoorLocationSurpportBuidlingName()", bdLocation.getIndoorLocationSurpportBuidlingName() + "", "返回支持室内定位的building名称"));
        beanList.add(new LocationBean("getIndoorNetworkState()", bdLocation.getIndoorNetworkState() + "", "返回室内定位网络状态 #INDOOR_NETWORK_STATE_HIGH, #INDOOR_NETWORK_STATE_LOW, #INDOOR_NETWORK_STATE_MIDDLE"));
        beanList.add(new LocationBean("getLatitude()", bdLocation.getLatitude() + "", "获取纬度坐标"));
        beanList.add(new LocationBean("getLocationDescribe()", bdLocation.getLocationDescribe() + "", "获取位置语义化信息，没有的话返回NULL"));
        beanList.add(new LocationBean("getLocationID()", bdLocation.getLocationID() + "", "返回位置ID数据"));
        beanList.add(new LocationBean("getLocationWhere()", bdLocation.getLocationWhere() + "", "获取当前定位是国内还是国外"));
        beanList.add(new LocationBean("getLocType()", mSparseArray.get(bdLocation.getLocType()) + "", "获取定位类型: 参考 定位结果描述 相关的字段"));
        beanList.add(new LocationBean("getLocTypeDescription()", bdLocation.getLocTypeDescription() + "", "获取定位类型相关描述信息"));
        beanList.add(new LocationBean("getLongitude()", bdLocation.getLongitude() + "", "获取经度坐标"));
        beanList.add(new LocationBean("getNetworkLocationType()", bdLocation.getNetworkLocationType() + "", "在网络定位结果的情况下，获取网络定位结果是通过基站定位得到的还是通过wifi定位得到的还是GPS得结果\n\"wf\"： wifi定位结果 “cl“； cell定位结果 “ll”：GPS定位结果 null 没有获取到定位结果采用的类型"));
        int operators = bdLocation.getOperators();
        String operatorStr;
        if (operators == BDLocation.OPERATORS_TYPE_MOBILE) {
            operatorStr = "中国移动";
        }else if (operators == BDLocation.OPERATORS_TYPE_UNICOM) {
            operatorStr = "中国联通";
        }else if (operators == BDLocation.OPERATORS_TYPE_TELECOMU) {
            operatorStr = "中国电信";
        }else {
            operatorStr = "未知";
        }
        beanList.add(new LocationBean("getOperators()", operatorStr + "", "获取运营商信息"));
        StringBuilder sb = new StringBuilder();
        for (Poi poi : bdLocation.getPoiList()) {
            sb.append(poi.getName()).append("\n");
        }
        beanList.add(new LocationBean("getPoiList()", sb.toString() + "", "仅在开发者设置需要POI信息时才会返回，在网络不通或无法获取时有可能返回null"));
        beanList.add(new LocationBean("getProvince()", bdLocation.getProvince() + "", "获取省份"));
        beanList.add(new LocationBean("getRadius()", bdLocation.getRadius() + "", "获取定位精度,默认值0.0f"));
        beanList.add(new LocationBean("getSatelliteNumber()", bdLocation.getSatelliteNumber() + "", "gps定位结果时，获取gps锁定用的卫星数"));
        beanList.add(new LocationBean("getSpeed()", bdLocation.getSpeed() + "", "获取速度，仅gps定位结果时有速度信息，单位公里/小时，默认值0.0f"));
        beanList.add(new LocationBean("getStreet()", bdLocation.getStreet() + "", "获取街道信息"));
        beanList.add(new LocationBean("getStreetNumber()", bdLocation.getStreetNumber() + "", "获取街道号码"));
        beanList.add(new LocationBean("getTime()", bdLocation.getTime() + "", "server返回的当前定位时间"));
        beanList.add(new LocationBean("getUserIndoorState()", bdLocation.getUserIndoorState() + "", "返回用户室内外状态，#USER_INDDOR_TRUE,#USER_INDOOR_FALSE,#USER_INDOOR_UNKNOW"));
        beanList.add(new LocationBean("hasAddr()", bdLocation.hasAddr() + "", "是否有地址信息"));
        beanList.add(new LocationBean("hasAltitude()", bdLocation.hasAltitude() + "", "是否包含高度信息"));
        beanList.add(new LocationBean("hasRadius()", bdLocation.hasRadius() + "", "是否包含半径信息"));
        beanList.add(new LocationBean("hasSateNumber() ", bdLocation.hasSateNumber() + "", ""));
        beanList.add(new LocationBean("hasSpeed()", bdLocation.hasSpeed() + "", "是否包含速度信息"));
        beanList.add(new LocationBean("isIndoorLocMode()", bdLocation.isIndoorLocMode() + "", "是否处于室内定位模式"));


        return beanList;
    }


    //61	GPS定位结果，GPS定位成功
    //62	无法获取有效定位依据，定位失败，请检查运营商网络或者WiFi网络是否正常开启，尝试重新请求定位
    //63	网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位
    //66	离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果
    //67	离线定位失败
    //161	网络定位结果，网络定位成功
    //162	请求串密文解析失败，一般是由于客户端SO文件加载失败造成，请严格参照开发指南或demo开发，放入对应SO文件
    //167	服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位
    //505	AK不存在或者非法，请按照说明文档重新申请AK

    private static SparseArray<String> mSparseArray = new SparseArray<>();

    static {
        mSparseArray.put(61, "GPS定位结果，GPS定位成功");
        mSparseArray.put(62, "无法获取有效定位依据，定位失败，请检查运营商网络或者WiFi网络是否正常开启，尝试重新请求定位");
        mSparseArray.put(63, "网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位");
        mSparseArray.put(66, "离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果");
        mSparseArray.put(67, "离线定位失败");
        mSparseArray.put(161, "网络定位结果，网络定位成功");
        mSparseArray.put(162, "请求串密文解析失败，一般是由于客户端SO文件加载失败造成，请严格参照开发指南或demo开发，放入对应SO文件");
        mSparseArray.put(167, "服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位");
        mSparseArray.put(505, "AK不存在或者非法，请按照说明文档重新申请AK");
    }
}
