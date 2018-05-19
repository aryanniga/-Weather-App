package com.zedapps.zweather.service;

import android.content.Context;
import android.os.AsyncTask;

import com.zedapps.zweather.R;
import com.zedapps.zweather.model.WeatherData;
import com.zedapps.zweather.util.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * @author Shamah M Zoha
 * @since 5/17/18
 */
public class WeatherDataFetcher extends AsyncTask<Object, JSONObject, WeatherData> {

    private static final String WEATHER_REQUEST_URL = "http://api.openweathermap.org/data" +
            "/2.5/weather?lat={LAT}&lon={LON}&APPID={APIKEY}&units=metric";

    @Override
    protected WeatherData doInBackground(Object[] objects) {
        Context context = (Context) objects[0];
        String lat = (String) objects[1];
        String lon = (String) objects[2];

        String weatherApiKey = context.getResources().getString(R.string.weatherApiKey);

        try {
            URL weatherRequestUrl = new URL(WEATHER_REQUEST_URL.replace("{LAT}", lat)
                    .replace("{LON}", lon)
                    .replace("{APIKEY}", weatherApiKey));

            String response = NetworkUtils.obtainResponseString(weatherRequestUrl);

            return new WeatherData(new JSONObject(response));
        } catch (java.io.IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
