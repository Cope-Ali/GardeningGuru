package com.example.alimc.gardeningguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Parcelable;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    public static final String GARDEN_NAME = "mainGarden";
    static Garden garden;
    SharedPreferences mPrefs;
    TextView displayZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayZone = findViewById(R.id.viewZone);
        //initialize garden, or load garden if one already exists
        mPrefs = this.getPreferences(MODE_PRIVATE);
        if (!mPrefs.contains("garden")) {
            garden = new Garden(GARDEN_NAME);
        } else {
            Gson gson = new Gson();
            String json = mPrefs.getString("garden", "");
            garden = gson.fromJson(json, Garden.class);
            if (garden.getZone() != null) {
                String string_zone = garden.getZone().getUSDAcode();
                displayZone.setText(string_zone);
            }
        }

         //show garden myPrefs
        Toast toast=Toast.makeText(getApplicationContext(),mPrefs.getString("garden", ""),Toast.LENGTH_SHORT);
        toast.show();

        String string_zone = garden.getZone().getUSDAcode();
        displayZone.setText(string_zone);

    }
    public void launchZoneLookup(View v) {
        Intent intent = new Intent(this, ZoneLookup.class);
        startActivity(intent);
        //show garden
        Toast toast=Toast.makeText(getApplicationContext(),garden.getZone().getUSDAcode(),Toast.LENGTH_SHORT);
        toast.show();
    }
//TODO get zone to update from garden.zone.getUSDAcode after it is reset

    @Override
    protected void onPause() {
        super.onPause();
        //for testing
        //garden.setZone(new Zone("85041"));
        //

        mPrefs = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(garden); // myObject - instance of MyObject
        prefsEditor.putString("garden", json);
        prefsEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //show garden
        Toast toast=Toast.makeText(getApplicationContext(),garden.getZone().getUSDAcode(),Toast.LENGTH_SHORT);
        toast.show();
        String string_zone = garden.getZone().getUSDAcode();
        displayZone.setText(string_zone);
    }

    public void addPlantOnClick(View view){

        Intent intent = new Intent(this, AddPlant.class);
        startActivity(intent);
    }

}
