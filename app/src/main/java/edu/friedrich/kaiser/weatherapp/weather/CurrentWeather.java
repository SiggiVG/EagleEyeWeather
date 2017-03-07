package edu.friedrich.kaiser.weatherapp.weather;

import android.util.Log;

import org.json.JSONException;

/**
 * Created by Friedrich on 2/27/2017.
 */

public class CurrentWeather extends Weather
{
    double longitude = 0;
    double latitude = 0;
    String forecast= "";
    String forecastDescript= "";
    double temp = 0;
    double pressure = 0;
    double humid = 0;
    double tempMin = 0;
    double tempMax = 0;
    double windSpeed = 0;
    int windDegrees = 0;
    String loc = "";

    public CurrentWeather(String str) throws JSONException {
        super(str);
        this.parseJson();
    }

    @Override
    public Weather parseJson() throws JSONException {
        //TODO: parse the JSON file, putting each value within to a variable above.
        this.longitude = json.getJSONObject("coord").getDouble("lon");
        Log.d("WEATHER","Longitude = " + this.longitude);

        return this;
    }
}
