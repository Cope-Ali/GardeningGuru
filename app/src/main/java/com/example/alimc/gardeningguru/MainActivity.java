package com.example.alimc.gardeningguru;

// Ali Cope can access and update project
//Push to Git by Matthew
// Here is a conflict comment
// Bob had pizza for dinner

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchZoneLookup(View v) {
                Intent intent = new Intent(this, ZoneLookup.class);
                startActivity(intent);
            }



    }
