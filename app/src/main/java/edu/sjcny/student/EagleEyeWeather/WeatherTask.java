package edu.sjcny.student.EagleEyeWeather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;

/**
 * Created by Mattyboy on 3/8/2017.
 */

public class WeatherTask extends AsyncTask<String, String, String>
{

    @Override
    protected void onPreExecute()
    {

    }

    @Override
    protected String doInBackground(String... params)
    {
        HttpURLConnection request = null;
        BufferedReader reader = null;

        try
        {
            //This is a url
            URL url = new URL(params[0]);
            //opens the door
            request = (HttpURLConnection) url.openConnection();
            //walks in
            request.connect();

            //get's the shit shit that's thrown at you
            InputStream stream = request.getInputStream();

            //reader then poops the shit into a string
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                //removes all new lines???
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }

            return buffer.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(request != null)//if got something, gtfo
                request.disconnect();
            try
            {
                if (reader != null)//close that shite
                    reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {
        //aftercare
    }
}
