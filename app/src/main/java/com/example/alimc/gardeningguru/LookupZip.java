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
import java.util.StringTokenizer;

public class LookupZip extends AsyncTask<String, CharSequence, String> {

    Zone _zone;
    private String _zip;
    private Context _currentContext;
    private String API_URL;
    @SuppressLint("StaticFieldLeak")
    private TextView _viewZone;

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

       // String zone_info_string = String.valueOf(values);
        // split into parts
        //StringTokenizer st = new StringTokenizer(zone_info_string);
        //String zone = st.nextToken();
        //String coord = st.nextToken();
        //String lat = st.nextToken();
        //String lon = st.nextToken();
        //String temp = st.nextToken();
        //assign to garden.zone variables
        //StringTokenizer st2 = new StringTokenizer(zone, ":");
        //_zone.setUSDAcode(st2.nextToken());
        //StringTokenizer st3 = new StringTokenizer(temp, ":");
        //_zone.setTempRange(st3.nextToken());

    }


}

