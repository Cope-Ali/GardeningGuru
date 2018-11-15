package com.example.alimc.gardeningguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    Garden garden;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize garden, or load garden if one already exists
        mPrefs = this.getPreferences(MODE_PRIVATE);
        if (!mPrefs.contains("garden")) {
            garden = new Garden("testGarden"); /* todo ask user to name garden */
        }
        else {
            Gson gson = new Gson();
            String json = mPrefs.getString("garden", "");
            garden = gson.fromJson(json, Garden.class);
        }

        /* show garden
        Toast toast=Toast.makeText(getApplicationContext(),mPrefs.getString("garden", ""),Toast.LENGTH_SHORT);
        toast.show();
        */
    }

    public void launchZoneLookup(View v) {
        Intent intent = new Intent(this, ZoneLookup.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //for testing
        garden.setZone(new Zone("85041"));
        //

        mPrefs = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(garden); // myObject - instance of MyObject
        prefsEditor.putString("garden", json);
        prefsEditor.apply();
    }
}
