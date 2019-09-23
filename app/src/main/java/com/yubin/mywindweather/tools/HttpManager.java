package com.yubin.mywindweather.tools;

import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.yubin.mywindweather.application.MyApplication;
import com.yubin.mywindweather.modle.weather.DataBean;
import com.yubin.mywindweather.myInterface.GetCityNameListener;
import com.yubin.mywindweather.myInterface.GetWeatherDataListener;
import com.yubin.mywindweather.util.LogUtil;
import com.yubin.mywindweather.util.NetUtils;
import com.yubin.mywindweather.util.TestUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by YUBIN at 17-11-2 下午5:37
 * Last modified at 17-8-8 下午6:09
 */

/**
 * Created by YUBIN at 17-7-24 下午2:59
 * Last modified at 17-7-24 下午2:59
 */

public class HttpManager {

    /**
     * 获取本机外网IP
     *
     * @return
     */
    public static String GetNetIp() {

        if (!MyApplication.isRelease) {
            return "127.0.0.1";
        }

        if (MyApplication.useRandomWeather) {
            return "127.0.0.1";
        }

        URL infoUrl = null;
        InputStream inStream = null;
        String line = "";
        try {
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // 从反馈的结果中提取出IP地址
                int start = strber.indexOf("{");
                int end = strber.indexOf("}");
                String json = strber.substring(start, end + 1);
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        line = jsonObject.optString("cip");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LogUtil.show("ip=====" + line);
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * 通过IP定位城市
     *
     * @param ipAddress
     * @param listener
     */
    public static void getCityName(String ipAddress, final GetCityNameListener listener) {

        if (!MyApplication.isRelease) {
            listener.onResult("随机城市");
            return;
        }

        if (MyApplication.useRandomWeather) {
            listener.onResult("随机城市");
            return;
        }
        //http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=116.31.83.44
        //http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=124.160.217.3
        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=" + ipAddress;
        LogUtil.show(url);
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //1	-1	-1	中国	广东	深圳
                LogUtil.show("=========" + response);
                String result = response.trim();
                LogUtil.show("=========" + result);
                result = result.substring(result.lastIndexOf("\t") + 1, result.length());
                LogUtil.show("=========" + result);
                if (!TextUtils.isEmpty(result)) {
                    listener.onResult(result);
                } else {
                    listener.onError("定位城市失败！");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError("无法定位城市！");
            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * 通过城市获取天气数据
     *
     * @param cityName
     * @param listener
     */
    public static void getWeather(String cityName, final GetWeatherDataListener listener) {
        //http://wthrcdn.etouch.cn/weather_mini?city=深圳
        String city = cityName;
        LogUtil.show("1=" + city);
        try {
            city = URLEncoder.encode(city, "utf-8");
            LogUtil.show("2=" + city);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.show("编码失败！");
        }
        LogUtil.show("3=" + city);
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        LogUtil.show(url);
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        final Gson gson = MyApplication.getGson();
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    LogUtil.show("=====================================");
                    LogUtil.show("json=" + jsonObject.toString());
                    String test = URLDecoder.decode(jsonObject.toString(), "Unicode");
                    LogUtil.show("test=test=test=test=test=test=test=test=test=test=");
                    LogUtil.show("test=" + test);
                    String desc = jsonObject.getString("desc").toString();
                    if (desc.equals("OK")) {
                        String dataString = jsonObject.getString("data").toString();
                        DataBean data = gson.fromJson(dataString, DataBean.class);
                        LogUtil.show(data.getCity() + "   " + data.getForecast().get(0).getType() + "   " + data.getWendu());
                        for (int i = 0; i < data.getForecast().size(); i++) {
                            LogUtil.show("------------------------------");
                            LogUtil.show("日期：" + data.getForecast().get(i).getDate());
                            LogUtil.show("天气：" + data.getForecast().get(i).getType());
                            LogUtil.show("风力：" + data.getForecast().get(i).getFengli());
                            LogUtil.show("高温：" + data.getForecast().get(i).getHigh());
                            LogUtil.show("低温：" + data.getForecast().get(i).getLow());
                        }
                    } else {
                        listener.onError("数据错误！");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    listener.onError("数据错误！");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError("获取天气信息失败！");
            }
        });
        requestQueue.add(request);
    }

    public static void getWeatherNoVolley(String cityName, final GetWeatherDataListener listener, boolean getNewData) {
        //http://wthrcdn.etouch.cn/weather_mini?city=深圳
        String city = cityName;
        try {
            city = URLEncoder.encode(city, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.show("编码失败！");
        }
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        LogUtil.show(url);
        String result;
        if (MyApplication.isRelease) {
            if (MyApplication.useRandomWeather) {
                if (getNewData) {
                    result = TestUtil.getNewTestData();
                } else {
                    result = TestUtil.getLastTestData();
                }
            } else {
                result = NetUtils.get(url);
            }
        } else {
            result = TestUtil.getNewTestData();
        }

        Gson gson = MyApplication.getGson();
        LogUtil.show("result=" + result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            String desc = jsonObject.getString("desc").toString();
            if (desc.equals("OK")) {
                String dataString = jsonObject.getString("data").toString();
                DataBean data = gson.fromJson(dataString, DataBean.class);
                LogUtil.show(data.getCity() + "   " + data.getForecast().get(0).getType() + "   " + data.getWendu());
                data=removeRubbishCode(data);
                for (int i = 0; i < data.getForecast().size(); i++) {
                    LogUtil.show("------------------------------");
                    LogUtil.show("日期：" + data.getForecast().get(i).getDate());
                    LogUtil.show("天气：" + data.getForecast().get(i).getType());
                    LogUtil.show("风力：" + data.getForecast().get(i).getFengli());
                    LogUtil.show("高温：" + data.getForecast().get(i).getHigh());
                    LogUtil.show("低温：" + data.getForecast().get(i).getLow());
                }
                listener.onResult(data);
            } else {
                listener.onError("数据错误！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            listener.onError("获取天气信息失败！");
        }
    }

    private static DataBean removeRubbishCode(DataBean dataBean) {
        for (int i = 0; i < dataBean.getForecast().size(); i++) {
            if (dataBean.getForecast().get(i).getFengli().contains("18")) {
                dataBean.getForecast().get(i).setFengli("超强台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("17")) {
                dataBean.getForecast().get(i).setFengli("超强台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("16")) {
                dataBean.getForecast().get(i).setFengli("超强台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("15")) {
                dataBean.getForecast().get(i).setFengli("强台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("14")) {
                dataBean.getForecast().get(i).setFengli("强台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("13")) {
                dataBean.getForecast().get(i).setFengli("强台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("12")) {
                dataBean.getForecast().get(i).setFengli("台风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("11")) {
                dataBean.getForecast().get(i).setFengli("暴风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("10")) {
                dataBean.getForecast().get(i).setFengli("狂风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("9")) {
                dataBean.getForecast().get(i).setFengli("烈风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("8")) {
                dataBean.getForecast().get(i).setFengli("大风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("7")) {
                dataBean.getForecast().get(i).setFengli("劲风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("6")) {
                dataBean.getForecast().get(i).setFengli("强风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("5")) {
                dataBean.getForecast().get(i).setFengli("清风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("4")) {
                dataBean.getForecast().get(i).setFengli("和风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("3")) {
                dataBean.getForecast().get(i).setFengli("微风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("2")) {
                dataBean.getForecast().get(i).setFengli("轻风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("1")) {
                dataBean.getForecast().get(i).setFengli("软风");
            } else if (dataBean.getForecast().get(i).getFengli().contains("0")) {
                dataBean.getForecast().get(i).setFengli("无风");
            }
        }
        return dataBean;
    }

}
