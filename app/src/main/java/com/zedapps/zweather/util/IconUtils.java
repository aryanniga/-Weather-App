package com.zedapps.zweather.util;

import com.zedapps.zweather.R;

/**
 * @author smzoha
 * @since 5/18/18
 */
public class IconUtils {
    private static final String CLEAR_DAY = "01d";
    private static final String CLEAR_NIGHT = "01n";

    private static final String FEW_CLOUDS_DAY = "02d";
    private static final String FEW_CLOUDS_NIGHT = "02n";

    private static final String SCATTERED_CLOUDS_DAY = "03d";
    private static final String SCATTERED_CLOUDS_NIGHT = "03n";

    private static final String BROKEN_CLOUDS_DAY = "04d";
    private static final String BROKEN_CLOUDS_NIGHT = "04n";

    private static final String SHOWER_RAIN_DAY = "09d";
    private static final String SHOWER_RAIN_NIGHT = "09n";

    private static final String RAIN_DAY = "10d";
    private static final String RAIN_NIGHT = "10n";

    private static final String THUNDERSTORM_DAY = "11d";
    private static final String THUNDERSTORM_NIGHT = "11n";

    private static final String SNOW_DAY = "13d";
    private static final String SNOW_NIGHT = "13n";

    private static final String HAZE_DAY = "50d";
    private static final String HAZE_NIGHT = "50n";

    public static int getIconDrawableCode(String code) {
        switch (code) {
            case CLEAR_DAY:
                return R.drawable.clearsun;

            case CLEAR_NIGHT:
                return R.drawable.clearmoon;

            case FEW_CLOUDS_DAY:
                return R.drawable.cloudysun;

            case FEW_CLOUDS_NIGHT:
                return R.drawable.cloudymoon;

            case SCATTERED_CLOUDS_DAY:
            case SCATTERED_CLOUDS_NIGHT:
                return R.drawable.scattercloud;

            case BROKEN_CLOUDS_DAY:
            case BROKEN_CLOUDS_NIGHT:
                return R.drawable.brokencloud;

            case SHOWER_RAIN_DAY:
            case SHOWER_RAIN_NIGHT:
                return R.drawable.showerrain;

            case RAIN_DAY:
                return R.drawable.rainday;

            case RAIN_NIGHT:
                return R.drawable.rainnight;

            case THUNDERSTORM_DAY:
            case THUNDERSTORM_NIGHT:
                return R.drawable.thunderstorm;

            case SNOW_DAY:
            case SNOW_NIGHT:
                return R.drawable.snow;

            case HAZE_DAY:
            case HAZE_NIGHT:
                return R.drawable.haze;
        }

        return -1;
    }
}
