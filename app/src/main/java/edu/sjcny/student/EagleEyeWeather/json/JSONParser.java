package edu.sjcny.student.EagleEyeWeather.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sjcny.student.EagleEyeWeather.weather.CurrentWeather;
import edu.sjcny.student.EagleEyeWeather.weather.Weather;
import edu.sjcny.student.EagleEyeWeather.weather.WeeklyWeather;

/**
 * Created by Friedrich on 3/7/2017.
 */

public class JSONParser
{
    /**
     * Ths one is for current weather objects
     * @param strJSON A string that has the JSON object in it
     * @return a single weather object that is the current weather
     */
    public static CurrentWeather parseJSONWeather(String strJSON) throws JSONException
    {
        Log.d("ParseJSON",strJSON);
        JSONObject json = new JSONObject(strJSON);

        CurrentWeather curWeather = new CurrentWeather();

        JSONObject weather = ((JSONObject)json.getJSONArray("weather").get(0));
        curWeather.forecast = weather.getString("main");
        curWeather.forecastDescript = weather.getString("description");

        JSONObject main = json.getJSONObject("main");
        curWeather.temp = Double.parseDouble(main.getString("temp"));
        curWeather.tempMin = Double.parseDouble(main.getString("temp_min"));
        curWeather.tempMax = Double.parseDouble(main.getString("temp_max"));

        curWeather.loc = json.getString("name");

        Log.d("ParseJSON", curWeather.forecast + " " + curWeather.forecastDescript + " "
                + curWeather.temp + " " + curWeather.tempMin + " " + curWeather.tempMax + " " + curWeather.loc);

        return curWeather;
    }

    /**
     * This one is for 7 day forecast
     * @param strJSON A string that has the JSON object in it
     * @return an array of forecasted weather objects
     * @throws JSONException
     */
    public static WeeklyWeather[] parseJSONForecast(String strJSON) throws JSONException
    {
        JSONObject json = new JSONObject(strJSON);
        return null;
    }

}
