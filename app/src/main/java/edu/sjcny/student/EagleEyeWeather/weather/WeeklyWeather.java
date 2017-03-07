package edu.sjcny.student.EagleEyeWeather.weather;

import org.json.JSONException;

/**
 * Created by Friedrich on 2/27/2017.
 */

public class WeeklyWeather extends Weather
{
    public WeeklyWeather(String str) throws JSONException {
        super(str);
        this.parseJson();
    }

    @Override
    public Weather parseJson()
    {
        //TODO: parse the JSON file, putting each value within to a variable above.

        return this;
    }
}
