package edu.sjcny.student.EagleEyeWeather.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static edu.sjcny.student.EagleEyeWeather.MainActivity.appKey;

/**
 * Created by Friedrich on 2/27/2017.
 *
 * This doesn't work?
 */

public class WeatherURLHandler //implements Runnable
{
    //Weather currentWeather;
    //Weather weeklyWeather;

    //TODO:
    /*public WeatherURLHandler(CurrentWeather currentWeather, WeeklyWeather weeklyWeather)
    {
        this.currentWeather = currentWeather;
        this.weeklyWeather = weeklyWeather;
    }*/

    /*@Override
    public void run()
    {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        Log.d("WEATHER", "Weather JSON Retrieval Thread Started");

        try {
            //Log.d("JSON", readUrl(curWeather.getText().toString()));
            //currentWeather = new CurrentWeather(readUrl(getWeatherAPICALL("11772", "imperial")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //Log.d("JSON", readUrl(day7Weather.getText().toString()));
            //weeklyWeather = new WeeklyWeather(readUrl(getWeatherAPICALL("11772", "imperial", 7)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //TODO have urls formed using lat and long instead
    // replace "?zip=" with "?lat=" + lat + "&lon=" + lon

    public static String getWeatherAPICALL(String zip, String units)
    {
        return "http://api.openweathermap.org/data/2.5/" + "weather" + "?zip=" + zip + "&units=" + units + "&appid=" + appKey;
    }

    public static String getWeatherAPICALL(String lat, String lon, String units)
    {
        return "http://api.openweathermap.org/data/2.5/" + "weather" + "?lat=" + lat + "&lon=" + lon + "&units=" + units + "&appid=" + appKey;
    }

    public static String getWeatherAPICALL(String zip, String units, int cnt)
    {
        return "http://api.openweathermap.org/data/2.5/" + "forecast/daily" + "?zip=" + zip + "&units=" + units + "&cnt=" + cnt + "&appid=" + appKey;
    }

    public static String getWeatherAPICALL(String lat, String lon, String units, int cnt)
    {
        return "http://api.openweathermap.org/data/2.5/" + "forecast/daily" + "?lat=" + lat + "&lon=" + lon + "&units=" + units + "&cnt=" + cnt + "&appid=" + appKey;
    }

    public  static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
