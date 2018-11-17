package com.example.alimc.gardeningguru;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.StringTokenizer;

public class ZoneLookup extends AppCompatActivity {
   private TextView ZoneView;
    String zipInput;
    String url = "https://phzmapi.org/";

    //public ZoneLookup() {
   // }
  //  SharedPreferences mPrefs = this.getPreferences(MODE_PRIVATE);
    //Gson gson = new Gson();
    //String json = mPrefs.getString("garden", "");
    //Garden garden = gson.fromJson(json, Garden.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonelookup);
        // create button function
        Button btnLookup = findViewById(R.id.submitZip);
        //create textView function
        ZoneView = findViewById(R.id.zoneViewLookup);



        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.zipInput);
                zipInput = editText.getText().toString();
                LookupZip l = new LookupZip(zipInput, ZoneLookup.this, url, ZoneView);
              //  garden.getZone().setZip(zipInput);
                l.execute();
            }
        });

    }
}


