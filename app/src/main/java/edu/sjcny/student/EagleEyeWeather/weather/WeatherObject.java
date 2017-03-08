package edu.sjcny.student.EagleEyeWeather.weather;

/**
 * Created by Friedrich on 3/8/2017.
 */

public class WeatherObject {
    private String city;

    private String percip = "";
    private String percipDesc = "";

    private double avgTemp = 0;
    private double minTemp = 0;
    private double maxTemp = 0;
    private double nightTemp = 0;
    private double eveTemp = 0;
    private double mornTemp = 0;
    private double windspeed = 0;

    private double pressure = 0;
    private double humidity = 0;
    //private String cloud;
    //private String cloudtype;

    //private double day;

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windsped) {
        this.windspeed = windsped;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public String isPercip() {
        return percip;
    }

    public void setPercip(String percip) {
        this.percip = percip;
    }

    public String getPercipDesc() {
        return percipDesc;
    }

    public void setPercipDesc(String percipDesc) {
        this.percipDesc = percipDesc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(double mornTemp) {
        this.mornTemp = mornTemp;
    }

    public double getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(double eveTemp) {
        this.eveTemp = eveTemp;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public String print()
    {
        return "Location: " + city + " Percipitation:  " + percip + " " + percipDesc + " Average Temperature: " +  avgTemp  + " Minimum Temperature: " + minTemp + " Maximum Temperature: " + maxTemp
                + " Tonight's Temperature: " + nightTemp  + " Evening's Temperature: " + eveTemp  + " Morning's Temperature: " + mornTemp  + " Windspeed: " + windspeed + " Pressure: "
                + pressure  + " Humidity: " + humidity;
    }
}