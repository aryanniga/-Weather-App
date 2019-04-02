package com.zedapps.zweather.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.zedapps.zweather.R;
import com.zedapps.zweather.model.TimeData;
import com.zedapps.zweather.util.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * @author Shamah M Zoha
 * @since 5/17/18
 */
public class TimeDataFetcher extends AsyncTask<Object, JSONObject, TimeData> {
    
    private static final String TIME_REQUEST_URL = "http://api.timezonedb.com/v2/get-time-zone?" +
            "key={APIKEY}&by=position&format=json&lat={LAT}&lng={LON}";

    @Override
    protected TimeData doInBackground(Object[] objects) {
        Log.d(this.getClass().getName(), "starting fetcher exec");

        Context context = (Context) objects[0];
        String lat = (String) objects[1];
        String lon = (String) objects[2];

        String timeApiKey = context.getResources().getString(R.string.timeApiKey);

        try {
            URL timeRequestUrl = new URL(TIME_REQUEST_URL.replace("{APIKEY}", timeApiKey)
                    .replace("{LAT}", lat)
                    .replace("{LON}", lon));

            String response = NetworkUtils.obtainResponseString(timeRequestUrl);

            return new TimeData(new JSONObject(response));
        } catch (java.io.IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
