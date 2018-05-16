package com.zedapps.zweather.service;

import android.content.Context;
import android.os.AsyncTask;

import com.zedapps.zweather.R;
import com.zedapps.zweather.model.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Shamah M Zoha
 * @since 5/17/18
 */
public class WeatherDataFetcher extends AsyncTask<Object, JSONObject, WeatherData> {

    private static final String REQUEST_URL = "http://api.openweathermap.org/data" +
            "/2.5/weather?lat={LAT}&lon={LON}&APPID={APIKEY}&units=metric";

    @Override
    protected WeatherData doInBackground(Object[] objects) {
        Context context = (Context) objects[0];
        String lat = (String) objects[1];
        String lon = (String) objects[2];

        String apiKey = context.getResources().getString(R.string.apiKey);

        try {
            URL requestUrl = new URL(REQUEST_URL.replace("{LAT}", lat)
                    .replace("{LON}", lon)
                    .replace("{APIKEY}", apiKey));

            System.out.println(requestUrl.toString());

            HttpURLConnection httpConnection = (HttpURLConnection) requestUrl.openConnection();
            httpConnection.setRequestMethod("GET");

            BufferedReader responseReader = new BufferedReader(new InputStreamReader
                    (httpConnection.getInputStream()));

            StringBuilder responseBuilder = new StringBuilder();
            String buffer;

            while ((buffer = responseReader.readLine()) != null) {
                responseBuilder.append(buffer).append("\n");
            }

            return new WeatherData(new JSONObject(responseBuilder.toString()));
        } catch (java.io.IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
