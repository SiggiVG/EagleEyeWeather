package edu.sjcny.student.EagleEyeWeather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.friedrich.kaiser.weatherapp.R;

import org.json.JSONException;

import edu.sjcny.student.EagleEyeWeather.json.JSONParser;
import edu.sjcny.student.EagleEyeWeather.url.WeatherURLHandler;
import edu.sjcny.student.EagleEyeWeather.weather.WeatherObject;

public class MainActivity extends AppCompatActivity {

    //TODO: move this to values/strings.xml
    public static final String appKey="2499865a319393f1770ce3daa85674da";

    //TODO remove these, to be replaced by the new UI, which populates from Weather Objects
    double curTemp=45;
    double temp1High=56;
    double temp1Low=40;
    double temp2High=70;
    double temp2Low=40;
    double temp3High=45;
    double temp3Low=42;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Location Start
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //   Consider calling
            //   ActivityCompat#requestPermissions
            //   here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            //   to handle the case where the user grants the permission. See the documentation
            //   for ActivityCompat#requestPermissions for more details.
            //   return TODO;
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 321);

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        /**
         * this call ensures that onLocationChanged has been called at least once.
         */
        locationListener.onLocationChanged(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        //Location End

        //This is the workaround for not using an Async Call
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());



        //TODO Initialize the GUI and Weather Objects (Current, WeekAhead[])

        //TODO have the chrome(?) browser open at the touch of a button to the Weather Channel page???

        //TODO Begin Async Call
        //TODO Use the Weather URL Handler in the Async Call to get JSON as String
        String jsonNow = "";
        String jsonAhead = "";
        try {
            jsonNow = WeatherURLHandler.readUrl(WeatherURLHandler.getWeatherAPICALL(((MyLocationListener)locationListener).getLatitude(), ((MyLocationListener)locationListener).getLongitude(), "imperial"));
            jsonAhead = WeatherURLHandler.readUrl(WeatherURLHandler.getWeatherAPICALL(((MyLocationListener)locationListener).getLatitude(), ((MyLocationListener)locationListener).getLongitude(), "imperial", 7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO end Async Call

        //TODO parse JSON using JSONObject API into Weather Objects
        WeatherObject weather;
        WeatherObject[] weekAhead;
        try {
            weather = JSONParser.parseJSONNow(jsonNow);
            weekAhead = JSONParser.parseJSONWeekAhead(jsonAhead);

            curTemp = weather.getAvgTemp();
            temp1Low = weather.getMinTemp();
            temp1High = weather.getMaxTemp();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //TODO Update the GUI Fields with information taken from the Weather Objects




        /*CurrentWeather currentWeather = null;
        WeeklyWeather weeklyWeather = null;
        WeatherURLHandler weatherRetriever = new WeatherURLHandler(currentWeather, weeklyWeather);*/

        //TODO: have this change based on if you're on current weather page or weekly forecast page
        populateWeatherFields();

    }

    /**
     * TODO: have it request permissions if they are not granted
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 123: //Fine Location
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission has been granted
                }
                else
                {
                    //permission not granted
                }
                break;
            case 321: //Coarse Location
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //permission has been granted
                }
                else
                {
                    //permission not granted
                }
        }
    }

    /**
     * TODO replace this with a new populate method that takes in Weather objects
     */
    private void populateWeatherFields()
    {
        String[] weathers = new String[] {
                "Now: " + curTemp + "\u00B0",
                "Today's High: " + temp1High + "\u00B0 Low: " + temp1Low + "\u00B0"};
                //"Tomorrow: " + temp2High + "*/" + temp2Low + "*",
                //"Thursday: " + temp3High + "*/" + temp3Low + "*" };

        ArrayAdapter adapter = new ArrayAdapter<String>(                this, R.layout.activity_listview, weathers);

        ListView listView = (ListView) findViewById(R.id.temps_listview);
        listView.setAdapter(adapter);
        listView.setDivider(null);
    }

    /**
     * This is an interface that retrieves GPS location data
     */
    private class MyLocationListener implements LocationListener {

        String longitude = "";

        public String getLongitude() {
            return longitude;
        }

        String latitude = "";

        public String getLatitude() {
            return latitude;
        }

        @Override
        public void onLocationChanged(Location loc) {

            //TODO truncate the decimal after a few places
            longitude = Double.toString(loc.getLongitude());
            Log.d("LOC", longitude);
            latitude = Double.toString(loc.getLatitude());
            Log.d("LOC", latitude);

        /*------- To get city name from coordinates -------- */
            /*String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //String s = longitude + "\n" + latitude + "\n\nMy Current City is: " + cityName;
            //editLocation.setText(s);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }


}
