package com.example.alimc.gardeningguru;

// Ali Cope can access and update project
//Push to Git by Matthew
// Here is a conflict comment
// Bob had pizza for dinner

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView ZoneView;
    String zipInput;
    String url = "https://phzmapi.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create button function
        Button btnLookup = findViewById(R.id.btnLookup);
        //create textView function
        ZoneView = findViewById(R.id.viewZone);

        /*btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.textZip);
                zipInput = editText.getText().toString();
                LookupZip l = new LookupZip(zipInput, MainActivity.this, url, ZoneView);
                l.execute();
            }
        });*/

    }
}
