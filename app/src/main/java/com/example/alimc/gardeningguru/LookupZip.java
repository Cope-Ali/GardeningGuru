package com.example.alimc.gardeningguru;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LookupZip extends AsyncTask<String, CharSequence, String> {

    private String _zip;
    private Context _currentContext;
    private String API_URL;
    private TextView _viewZone;

    LookupZip(String zip, Context theContext, String url, TextView ZoneView){
        _zip = zip;
        _currentContext = theContext;
        API_URL = url;
        _viewZone = ZoneView;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(API_URL + _zip + ".json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                    publishProgress(line);
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);

            return null;
        }
    }
    @Override
    protected void onProgressUpdate(CharSequence... values) {
        _viewZone.setText(values[0]);
        super.onProgressUpdate(values);
    }


}

