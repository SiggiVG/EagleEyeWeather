package edu.sjcny.student.EagleEyeWeather.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Friedrich on 2/27/2017.
 */

public abstract class Weather
{
    protected JSONObject json;

    public Weather(String str) throws JSONException {
        this.json = new JSONObject(str);
    }

    public abstract Weather parseJson() throws JSONException;
}
