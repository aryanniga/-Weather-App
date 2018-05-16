package com.zedapps.zweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * @author Shamah M Zoha
 * @since 5/17/18
 */
public class NetworkUtils {

    public static boolean isConnectedToInternet(Context appContext) {
        ConnectivityManager connManager = (ConnectivityManager)
                appContext.getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo mobileNetInfo = connManager.getNetworkInfo(TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connManager.getNetworkInfo(TYPE_WIFI);

        return (mobileNetInfo == null || mobileNetInfo.isConnectedOrConnecting())
                || (wifiNetInfo == null || wifiNetInfo.isConnectedOrConnecting());
    }
}