package com.example.alimc.gardeningguru;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
        Button btnSave = findViewById(R.id.saveZone);
        //create textView function
        ZoneView = findViewById(R.id.zoneViewLookup);


        btnLookup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.zipInput);
                zipInput = editText.getText().toString();
                Zone zone = garden.getZone();
                LookupZip l = new LookupZip(zipInput, zone, ZoneLookup.this, url, ZoneView);
                zone.setZip(zipInput);
                l.execute();

            }
        });

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Zone zone = garden.getZone();
                EditText editTextZip = findViewById(R.id.zipInput);
                zipInput = editTextZip.getText().toString();
                zone.setZip(zipInput);
                String stringZone = ZoneView.getText().toString();
                try {
                    JSONObject jObj = new JSONObject(stringZone);
                    String _code = jObj.getString("zone");
                    garden.getZone().setUSDAcode(_code);
                    String _temp = jObj.getString("temperature_range");
                    garden.getZone().setTempRange(_temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //set changed zone to main garden zone
                garden.setZone(zone);
                //show garden
                Toast toast=Toast.makeText(getApplicationContext(),garden.getZone().getUSDAcode(),Toast.LENGTH_SHORT);
                toast.show();

            }
        });


    }
}


