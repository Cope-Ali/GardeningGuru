/**
 * Async task, called by ZoneLookup
 * Connects to API, that returns zone info from a zip code.
 */

package com.example.alimc.gardeningguru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Ali Cope
 * @version 1.0
 * @since 1.0
 * LookupZip class extends AsyncTask to obtain the hardiness zone from online resource
 */
public class LookupZip extends AsyncTask<String, CharSequence, String> {


    Zone _zone;
    private String _zip;
    private Context _currentContext;
    private String API_URL;
    @SuppressLint("StaticFieldLeak")
    private TextView _viewZone;


    /**
     * LookupZip obtains the hardiness zone by passing in a zipCode and looking it up online
     * <p>
     *    method takes a zip code entered by user and sends it to API url. Receives back a JSON
     *    object that is parsed and save into Zone object within Garden object
     * </p>
     * @param zip - zip code entered by user
     * @param zone - zone object in garden
     * @param theContext - context of application
     * @param url - API url
     * @param ZoneView - view area where zone information is output.
     */
    LookupZip(String zip, Zone zone, Context theContext, String url, TextView ZoneView){
        _zip = zip;
        _currentContext = theContext;
        API_URL = url;
        _viewZone = ZoneView;
        _zone = zone;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... strings) {

        //open connection
        try {
            URL url = new URL(API_URL + _zip + ".json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.i("LookupZip", "Connected to zone lookup API.");
            //read input stream from api
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                    publishProgress(line);
                }
                bufferedReader.close();
                Log.i("LookupZip", "Read from zone lookup API.");
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
                Log.i("LookupZip", "Disconnected from zone lookup API.");
            }
        } catch (Exception e) {
            Log.e("LookupZip", "Zip code not found " + _zip + ":" + e.getMessage(), e);
            return null;
        }
    }

  

    @Override
    protected void onProgressUpdate(CharSequence... values) {
        _viewZone.setText(values[0]);
        super.onProgressUpdate(values);


    }


}

