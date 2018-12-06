package com.example.alimc.gardeningguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static final String GARDEN_NAME = "mainGarden";
    static Garden garden;
    public static SharedPreferences mPrefs;
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
            //todo populate data for testing, then remove it for final product
            Plant plant = new Plant("carrot");
            plant.setDaysTillGermination(21);
            plant.setDaysTillHarvest(40);
            plant.setPlantNotes("notes about the plant");
            plant.setRowSpacing((float)12);
            plant.setSeedSpacing((float)1.5);
            plant.setSowDepth((float).5);
            garden.addPlant(plant);
            Planting planting = new Planting(plant.getName() + (new Date()).toString(), plant);
            planting.setDaysTillThin(plant.getDaysTillGermination() +  3);
            planting.setLocation("bed 1");
            planting.setNotes("notes about the planting");
            planting.setPlantWhen(new Date());
            garden.addPlanting(planting);


        } else if (garden == null) {
            Gson gson = new Gson();
            String json = mPrefs.getString("garden", "");
            garden = gson.fromJson(json, Garden.class);
        }
        if (garden.getZone() != null) {
            String string_zone = garden.getZone().getUSDAcode();
            displayZone.setText(" Your hardiness zone is: " + string_zone);
        }

        garden.computeTasksPending();
    }
    public void launchZoneLookup(View v) {
        Intent intent = new Intent(this, ZoneLookup.class);
        startActivity(intent);
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

    public void addPlantOnClick(View view){

        Intent intent = new Intent(this, AddPlant.class);
        startActivity(intent);
    }

    public void taskViewOnClick(View view){

        //this is to test the task
        Task task1 = new Task("test1","plant1",new Date(2020,12,28),false,"test",0);
        Task task2 = new Task("test2","plant2",new Date(2020,1,28),false,"test",0);
        Task task3 = new Task("test3","plant3",new Date(2020,8,28),false,"test",0);
        garden.addTask(task1);
        garden.addTask(task2);
        garden.addTask(task3);

        Intent intent = new Intent(this, TaskList.class);
        startActivity(intent);
    }

}
