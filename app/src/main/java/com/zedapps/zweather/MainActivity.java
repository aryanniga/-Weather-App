package com.zedapps.zweather;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zedapps.zweather.model.WeatherData;
import com.zedapps.zweather.service.WeatherDataFetcher;
import com.zedapps.zweather.util.NetworkUtils;

import org.apache.commons.text.WordUtils;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.R.layout.simple_dropdown_item_1line;
import static android.widget.Toast.LENGTH_LONG;
import static com.zedapps.zweather.R.array.coordinates;
import static com.zedapps.zweather.R.array.country;
import static com.zedapps.zweather.R.string.err_msg_no_internet;
import static com.zedapps.zweather.util.IconUtils.getIconDrawableCode;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * @author Shamah M Zoha
 * @since 5/17/18
 */
public class MainActivity extends AppCompatActivity {

    public static final String TIME_FORMAT_12H = "hh:mm a";
    public static final String TIMESTAMP_FORMAT = "dd/MM/yyyy hh:mm:ss a";
    private static List<String> countryList;
    private static List<String> coordinateList;

    private AutoCompleteTextView cityAutoComplete;
    private TextView txtCityLabel;
    private ImageView imgWeatherIcon;

    private TextView txtCurrentTemperature;
    private TextView txtWeatherDesc;

    private TextView txtMinTemperature;
    private TextView txtMaxTemperature;
    private TextView txtHumidity;

    private TextView txtWindSpeed;
    private TextView txtWindDeg;

    private TextView txtSunrise;
    private TextView txtSunset;

    private TextView txtUpdatedStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearch = findViewById(R.id.btnSearch);
        cityAutoComplete = findViewById(R.id.cityAutocomplete);

        txtCityLabel = findViewById(R.id.txtCityLabel);
        txtCurrentTemperature = findViewById(R.id.txtCurrentTemperature);
        txtWeatherDesc = findViewById(R.id.txtWeatherDesc);

        txtMinTemperature = findViewById(R.id.txtMinTemp);
        txtMaxTemperature = findViewById(R.id.txtMaxTemp);
        txtHumidity = findViewById(R.id.txtHumidity);

        txtWindSpeed = findViewById(R.id.txtWindSpeed);
        txtWindDeg = findViewById(R.id.txtWindDeg);

        txtSunrise = findViewById(R.id.txtSunrise);
        txtSunset = findViewById(R.id.txtSunset);

        txtUpdatedStamp = findViewById(R.id.txtUpdatedTime);
        imgWeatherIcon = findViewById(R.id.imgWeatherIcon);

        populateLists();
        initializeCityList();

        if (!NetworkUtils.isConnectedToInternet(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), err_msg_no_internet, LENGTH_LONG).show();
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearchAction();
            }
        });
    }

    private void initializeCityList() {
        ArrayAdapter<String> cityListAdapter = new ArrayAdapter<>(this,
                simple_dropdown_item_1line, getResources().getStringArray(country));

        cityAutoComplete.setAdapter(cityListAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void handleSearchAction() {
        if (!NetworkUtils.isConnectedToInternet(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), err_msg_no_internet, LENGTH_LONG).show();
            return;
        }

        String cityCountryCombo = cityAutoComplete.getText().toString();

        if (countryList.contains(cityCountryCombo)) {
            String coordinates = coordinateList.get(countryList.indexOf(cityCountryCombo));
            String[] coordinatesComps = coordinates.split(",");
            AsyncTask<Object, JSONObject, WeatherData> weatherFetcher =
                    new WeatherDataFetcher().execute(getApplicationContext(), coordinatesComps[0],
                            coordinatesComps[1]);

            try {
                WeatherData weatherData = weatherFetcher.get();
                txtCityLabel.setText(cityCountryCombo);
                imgWeatherIcon.setBackgroundResource(getIconDrawableCode(weatherData.getWeatherCode()));

                txtCurrentTemperature.setText(weatherData.getCurrentTemperature());
                txtWeatherDesc.setText(WordUtils.capitalize(weatherData.getWeatherDescription()));

                txtMinTemperature.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.mintemp),
                        null, null, null);
                txtMinTemperature.setText(weatherData.getMinTemperature());

                txtMaxTemperature.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.maxtemp),
                        null, null, null);
                txtMaxTemperature.setText(weatherData.getMaxTemperature());

                txtHumidity.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.humidity),
                        null, null, null);
                txtHumidity.setText(weatherData.getHumidity());

                txtWindSpeed.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.windspeed),
                        null, null, null);
                txtWindSpeed.setText(weatherData.getWindSpeed());

                txtWindDeg.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.winddeg),
                        null, null, null);
                txtWindDeg.setText(weatherData.getWindDeg());

                txtSunrise.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.sunrise),
                        null, null, null);
                txtSunrise.setText(DateFormat.format(TIME_FORMAT_12H, weatherData.getSunriseTime()));

                txtSunset.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.sunset),
                        null, null, null);
                txtSunset.setText(DateFormat.format(TIME_FORMAT_12H, weatherData.getSunsetTime()));

                txtUpdatedStamp.setText("Last Update Time: " +
                        DateFormat.format(TIMESTAMP_FORMAT, weatherData.getLastUpdatedTime()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            txtCityLabel.setText(R.string.err_msg_invalid_city);
        }
    }

    private void populateLists() {
        countryList = unmodifiableList(asList(getResources().getStringArray(country)));
        coordinateList = unmodifiableList(asList(getResources().getStringArray(coordinates)));
    }
}