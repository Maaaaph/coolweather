package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import org.w3c.dom.Text;

/**
 * Created by haha on 2016/4/18.
 * 解析服务器返回的数据
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(
            CoolWeatherDB coolWeatherDB, String response) {

        //判断返回的是否为空字符串
        if ( !TextUtils.isEmpty(response)) {
            //按逗号分隔，返回的数据为一数组
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    //按单竖线分隔
                    String[] array = p.split("\\|"); //"\\|"表示"\|"
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据储存到Province表中
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
                                                            String response, int provinceId) {

        //判断返回的是否为空字符串
        if ( !TextUtils.isEmpty(response)) {
            //按逗号分隔，返回的数据为一数组
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    //按单竖线分隔
                    String[] array = c.split("\\|"); //"\\|"表示"\|"
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据储存到City表中
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
                                                            String response, int cityId) {

        //判断返回的是否为空字符串
        if ( !TextUtils.isEmpty(response)) {
            //按逗号分隔，返回的数据为一数组
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    //按单竖线分隔
                    String[] array = c.split("\\|"); //"\\|"表示"\|"
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据储存到County表中
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
