package com.zedapps.zweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * @author Shamah M Zoha
 * @since 5/17/18
 */
public class NetworkUtils {

    private static final String logTag = "networkUtil";

    public static boolean isNetworkNotConnected(Context appContext) {
        Log.d(logTag, "starting checking for connectivity");

        ConnectivityManager connManager = (ConnectivityManager)
                appContext.getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo mobileNetInfo = connManager.getNetworkInfo(TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connManager.getNetworkInfo(TYPE_WIFI);

        return (mobileNetInfo != null && !mobileNetInfo.isConnectedOrConnecting())
                && (wifiNetInfo != null && !wifiNetInfo.isConnectedOrConnecting());
    }

    public static String obtainResponseString(URL requestUrl) throws IOException {
        Log.d(logTag, "retrieving JSON data from: " + requestUrl);

        HttpURLConnection httpConnection = (HttpURLConnection) requestUrl.openConnection();
        httpConnection.setRequestMethod("GET");

        BufferedReader responseReader = new BufferedReader(new InputStreamReader
                (httpConnection.getInputStream()));

        StringBuilder responseBuilder = new StringBuilder();
        String buffer;

        while ((buffer = responseReader.readLine()) != null) {
            responseBuilder.append(buffer).append("\n");
        }

        Log.d(logTag, "successfully retrieved JSON data from provider");

        return responseBuilder.toString();
    }
}