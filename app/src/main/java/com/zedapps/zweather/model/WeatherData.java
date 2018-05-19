package com.zedapps.zweather.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * @author Shamah M Zoha
 * @since 5/18/18
 */
public class WeatherData {

    private String weatherCategory;
    private String weatherDescription;
    private String weatherCode;

    private String currentTemperature;
    private String pressure;
    private String humidity;
    private String minTemperature;
    private String maxTemperature;

    private String windSpeed;
    private String windDeg;

    private String cloudiness;

    private Date lastUpdatedTime;

    private Date sunriseTime;
    private Date sunsetTime;

    public WeatherData(JSONObject resposeJSon) throws JSONException {
        JSONObject weatherSection = resposeJSon.getJSONArray("weather").getJSONObject(0);
        this.weatherCategory = weatherSection.getString("main");
        this.weatherDescription = weatherSection.getString("description");
        this.weatherCode = weatherSection.getString("icon");

        JSONObject tempSection = resposeJSon.getJSONObject("main");
        this.currentTemperature = tempSection.getString("temp") + "°C";
        this.pressure = tempSection.getString("pressure") + "hPa";
        this.humidity = tempSection.getString("humidity") + "%";
        this.minTemperature = tempSection.getString("temp_min") + "°C";
        this.maxTemperature = tempSection.getString("temp_max") + "°C";

        if (resposeJSon.has("wind")) {
            JSONObject windSection = resposeJSon.getJSONObject("wind");

            if (windSection.has("speed")) {
                this.windSpeed = windSection.getString("speed") + "m/s";
            }

            if (windSection.has("deg")) {
                this.windDeg = windSection.getString("deg") + "°";
            }
        }

        long updateTimeStampMillis = parseUnixEpochToMilis(resposeJSon.getString("dt"));
        this.lastUpdatedTime = new Date(updateTimeStampMillis);

        JSONObject sysSection = resposeJSon.getJSONObject("sys");
        long sunriseMilis = parseUnixEpochToMilis(sysSection.getString("sunrise"));
        long sunsetMilis = parseUnixEpochToMilis(sysSection.getString("sunset"));

        this.sunriseTime = new Date(sunriseMilis);
        this.sunsetTime = new Date(sunsetMilis);
    }

    public String getWeatherCategory() {
        return weatherCategory;
    }

    public void setWeatherCategory(String weatherCategory) {
        this.weatherCategory = weatherCategory;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Date sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public Date getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Date sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    private long parseUnixEpochToMilis(String epoch) {
        return Long.parseLong(epoch) * 1000;
    }
}
