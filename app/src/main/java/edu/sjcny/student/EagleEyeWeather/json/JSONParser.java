package edu.sjcny.student.EagleEyeWeather.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sjcny.student.EagleEyeWeather.weather.WeatherObject;

/**
 * Created by Friedrich on 3/7/2017.
 */

public class JSONParser
{
    /**
     * this one is for /weather
     * This one is for current weather objects
     * @param strJSON A string that has the JSON object in it
     * @return a single weather object that is the current weather
     */
    public static WeatherObject parseJSONNow(String strJSON) throws JSONException
    {
        Log.d("JSON",strJSON);
        JSONObject json = new JSONObject(strJSON);

        WeatherObject curWeather = new WeatherObject();

        curWeather.setCity(json.getString("name"));

        JSONObject weath = ((JSONObject) json.getJSONArray("weather").get(0));
        curWeather.setPercip(weath.getString("main"));
        curWeather.setPercipDesc(weath.getString("description"));

        JSONObject main = json.getJSONObject("main");
        curWeather.setAvgTemp(Math.round(main.getDouble("temp")));
        curWeather.setHumidity(Math.round(main.getDouble("humidity")));
        curWeather.setPressure(Math.round(main.getDouble("pressure")));
        curWeather.setMinTemp(Math.round(main.getDouble("temp_min")));
        curWeather.setMaxTemp(Math.round(main.getDouble("temp_max")));

        curWeather.setWindspeed(json.getJSONObject("wind").getDouble("speed"));




        //http://api.openweathermap.org/data/2.5/forecast/daily?zip=11772,us&units=imperial&cnt=7&appid=2499865a319393f1770ce3daa85674da
        /*
        JSONObject weather = ((JSONObject)json.getJSONArray("weather").get(0));
        curWeather.forecast = weather.getString("main");
        curWeather.forecastDescript = weather.getString("description");

        JSONObject temp = json.getJSONObject("main");
        curWeather.temp = Double.parseDouble(main.getString("temp"));
        curWeather.tempMin = Double.parseDouble(main.getString("temp_min"));
        curWeather.tempMax = Double.parseDouble(main.getString("temp_max"));

        curWeather.loc = json.getString("name");

        Log.d("ParseJSON", curWeather.forecast + " " + curWeather.forecastDescript + " "
                + curWeather.temp + " " + curWeather.tempMin + " " + curWeather.tempMax + " " + curWeather.loc);
        */
        Log.d("WEATHER", curWeather.print());

        return curWeather;
    }

    /**
     * This one is for /forecast/daily
     * TODO check for it being a forecast/daily
     * @param strJSON A string that has the JSON object in it
     * @return an array of forecasted weather objects
     * @throws JSONException
     */
    public static WeatherObject[] parseJSONWeekAhead(String strJSON) throws JSONException
    {
        Log.d("JSON",strJSON);

        JSONObject json = new JSONObject(strJSON);

        String city = ((JSONObject)json.getJSONObject("city")).getString("name");

        JSONArray list = json.getJSONArray("list");
        WeatherObject[] weathers = new WeatherObject[list.length()];

        for(int i = 0; i < weathers.length; ++i)
        {
            weathers[i] = new WeatherObject();
            weathers[i].setCity(city);
            JSONObject weatherJSON = ((JSONObject) list.get(i));

            JSONObject weath = (JSONObject) weatherJSON.getJSONArray("weather").get(0);
            weathers[i].setPercip(weath.getString("main"));
            weathers[i].setPercipDesc(weath.getString("description"));

            JSONObject temps = weatherJSON.getJSONObject("temp");
            weathers[i].setAvgTemp(Math.round(temps.getDouble("day")));
            weathers[i].setMinTemp(Math.round(temps.getDouble("min")));
            weathers[i].setMaxTemp(Math.round(temps.getDouble("max")));
            weathers[i].setNightTemp(Math.round(temps.getDouble("night")));
            weathers[i].setEveTemp(Math.round(temps.getDouble("eve")));
            weathers[i].setMornTemp(Math.round(temps.getDouble("morn")));

            weathers[i].setPressure(weatherJSON.getDouble("speed"));
            weathers[i].setHumidity(weatherJSON.getDouble("speed"));

            weathers[i].setWindspeed(weatherJSON.getDouble("speed"));

            Log.d("WEATHER", weathers[i].print());
        }

        return weathers;
    }

}
