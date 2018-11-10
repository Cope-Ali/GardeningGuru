package com.example.alimc.gardeningguru;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ZoneLookup extends AppCompatActivity {
    TextView ZoneView;
    String zipInput;
    String url = "https://phzmapi.org/";

    public ZoneLookup() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonelookup);
        // create button function
        Button btnLookup = findViewById(R.id.submitZip);
        //create textView function
        ZoneView = findViewById(R.id.zoneView);

        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.zipInput);
                zipInput = editText.getText().toString();
                LookupZip l = new LookupZip(zipInput, ZoneLookup.this, url, ZoneView);
                l.execute();
            }
        });

    }
}


