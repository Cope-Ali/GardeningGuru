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

import org.json.JSONException;
import org.json.JSONObject;


import static com.example.alimc.gardeningguru.MainActivity.garden;

/**
 *ZoneLookup uses a user inputted zip code to get the USDA Hardiness Zone.
 * <p>
 *    Activity finds the zip code inputted by the user and passes it and
 *    the API web address into lookupZip method. It then enters all of the
 *    returned information into its place in the Zone object and saves the
 *    Zone to the Garden.
 *
 * </p>
 */
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

                //Zone zone = garden.getZone();
                //zone = new Zone();
                EditText editTextZip = findViewById(R.id.zipInput);
                //editTextZip = editTextZip.getText().toString();
                Zone zone = new Zone(editTextZip.getText().toString());
               // zone.setZip(zipInput);
                String stringZone = ZoneView.getText().toString();
                try {
                    JSONObject jObj = new JSONObject(stringZone);
                    String _code = jObj.getString("zone");
                    zone.setUSDAcode(_code);
                    String _temp = jObj.getString("temperature_range");
                    zone.setTempRange(_temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                garden.setZone(zone);
                garden.saveGarden();



            }
        });


    }
}


