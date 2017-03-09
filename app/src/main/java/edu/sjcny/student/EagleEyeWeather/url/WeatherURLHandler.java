package edu.sjcny.student.EagleEyeWeather.url;

import com.friedrich.kaiser.weatherapp.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import edu.sjcny.student.EagleEyeWeather.MainActivity;

/**
 * Created by Friedrich on 2/27/2017.
 *
 * This doesn't work?
 */

public class WeatherURLHandler //implements Runnable
{
    public static String getWeatherAPICALL(String appKey, String zip, String units)
    {
        return "http://api.openweathermap.org/data/2.5/" + "weather" + "?zip=" + zip + "&units=" + units + "&appid=" + appKey;
    }

    public static String getWeatherAPICALL(String appKey, String lat, String lon, String units)
    {
        return "http://api.openweathermap.org/data/2.5/" + "weather" + "?lat=" + lat + "&lon=" + lon + "&units=" + units + "&appid=" + appKey;
    }

    public static String getWeatherAPICALL(String appKey, String zip, String units, int cnt)
    {
        return "http://api.openweathermap.org/data/2.5/" + "forecast/daily" + "?zip=" + zip + "&units=" + units + "&cnt=" + cnt + "&appid=" + appKey;
    }

    public static String getWeatherAPICALL(String appKey, String lat, String lon, String units, int cnt)
    {
        return "http://api.openweathermap.org/data/2.5/" + "forecast/daily" + "?lat=" + lat + "&lon=" + lon + "&units=" + units + "&cnt=" + cnt + "&appid=" + appKey;
    }

    @Deprecated
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
