package edu.friedrich.kaiser.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.friedrich.kaiser.weatherapp.R;

import edu.friedrich.kaiser.weatherapp.weather.CurrentWeather;
import edu.friedrich.kaiser.weatherapp.weather.WeatherURLHandler;
import edu.friedrich.kaiser.weatherapp.weather.WeeklyWeather;

public class MainActivity extends AppCompatActivity {

    //TODO: move this to values/strings.xml
    public static final String appKey="2499865a319393f1770ce3daa85674da";

    //TODO remove these
    int curTemp=45;
    int temp1High=56;
    int temp1Low=40;
    int temp2High=70;
    int temp2Low=40;
    int temp3High=45;
    int temp3Low=42;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: not this
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());


        CurrentWeather currentWeather = null;
        WeeklyWeather weeklyWeather = null;
        WeatherURLHandler weatherRetriever = new WeatherURLHandler(currentWeather, weeklyWeather);

        //TODO: have this change based on if you're on current weather page or weekly forecast page
        populateWeatherFields();

    }

    private void populateWeatherFields()
    {
        String[] weathers = new String[] {
                "Now: " + curTemp + "*",
                "Today: " + temp1High + "*/" + temp1Low + "*",
                "Tomorrow: " + temp2High + "*/" + temp2Low + "*",
                "Thursday: " + temp3High + "*/" + temp3Low + "*" };

        ArrayAdapter adapter = new ArrayAdapter<String>(
                this, R.layout.activity_listview, weathers);

        ListView listView = (ListView) findViewById(R.id.temps_listview);
        listView.setAdapter(adapter);
        listView.setDivider(null);
    }




}
