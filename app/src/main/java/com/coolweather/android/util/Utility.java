package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces=new JSONArray(response);
                for (int i=0; i<allProvinces.length();i++){
                    JSONObject provinceobject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceobject.getString("name"));
                    province.setProvinceCode(provinceobject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities=new JSONArray(response);
                for (int i=0; i<allCities.length();i++){
                    JSONObject cityobject=allCities.getJSONObject(i);
                    City city=new City();
               city.setCityName(cityobject.getString("name"));
                   city.setCityCode(cityobject.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */

    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties=new JSONArray(response);
                for (int i=0; i<allCounties.length();i++){
                    JSONObject countyobject=allCounties.getJSONObject(i);
                County county=new County();
                county.setCountyName(countyobject.getString("name"));
                   county.setWeatherId(countyobject.getString("weather_id"));
                    county.setCityId(cityId);
                   county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }

    private static class TestUtils {
    }
}
