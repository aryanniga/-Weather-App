package com.zedapps.zweather.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * @author smzoha
 * @since 5/19/18
 */
public class TimeData {

    private Date currentTime;
    private String timeZone;

    public TimeData(JSONObject responseJson) throws JSONException {
        this.timeZone = responseJson.getString("zoneName");
        this.currentTime = new Date(parseUnixEpochToMilis(responseJson.getString("timestamp")));
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    private long parseUnixEpochToMilis(String epoch) {
        return Long.parseLong(epoch) * 1000;
    }
}
