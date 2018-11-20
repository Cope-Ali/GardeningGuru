package com.example.alimc.gardeningguru;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.StringTokenizer;

import static com.example.alimc.gardeningguru.MainActivity.garden;


public class ZoneLookup extends AppCompatActivity {
   private TextView ZoneView;
    String zipInput;
    String url = "https://phzmapi.org/";

    //public ZoneLookup() {
   // }

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
                Zone zone = garden.getZone();
                LookupZip l = new LookupZip(zipInput, zone, ZoneLookup.this, url, ZoneView);
                zone.setZip(zipInput);
                l.execute();

            }


            //get string
           // String zone_info_string = (String) ZoneView.getText();
            // split into parts
            //StringTokenizer st = new StringTokenizer(zone_info_string, ",");
            //String zone = st.nextToken();
            //String coord = st.nextToken();
            //String lat = st.nextToken();
            //String lon = st.nextToken();
            //String temp = st.nextToken();
            //assign to garden.zone variables
            // StringTokenizer st2 = new StringTokenizer(zone, ":");
            //garden.getZone().setUSDAcode(st2.nextToken());
            //StringTokenizer st3 = new StringTokenizer(temp, ":");
            //garden.getZone().setTempRange(st3.nextToken());
        });

    }
}


