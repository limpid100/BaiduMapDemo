package com.dxl.baidumapdemo;

/**
 * @author dxl
 * @date 2019/1/19 11:25
 */
public class LocationBean {
    String desc, method, result;

    public LocationBean(String method, String result, String desc) {
        this.desc = desc;
        this.method = method;
        this.result = result;
    }
}
