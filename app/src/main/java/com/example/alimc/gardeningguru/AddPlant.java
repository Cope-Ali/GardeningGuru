package com.example.alimc.gardeningguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.alimc.gardeningguru.MainActivity.garden;

public class AddPlant extends AppCompatActivity {

    Plant newPlant;
    EditText plantName;
    EditText sowDepth;
    EditText seedSpacing;
    EditText rowSpacing;
    EditText harvestDay;
    EditText plantNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        newPlant = new Plant();
        plantName = (EditText) findViewById(R.id.name);
        sowDepth = (EditText) findViewById(R.id.sowDepth);
        seedSpacing = (EditText) findViewById(R.id.seedSpacing);
        rowSpacing = (EditText) findViewById(R.id.rowSpacing);
        harvestDay = (EditText) findViewById(R.id.harvestDays);
        plantNotes = (EditText) findViewById(R.id.plantNotes);

    }


    public void onClickPlantSaveBtn(View v){

        newPlant.setName(plantName.getText().toString());
        newPlant.setSowDepth(Float.parseFloat(sowDepth.getText().toString()));
        newPlant.setRowSpacing(Float.parseFloat(rowSpacing.getText().toString()));
        newPlant.setSeedSpacing(Float.parseFloat(seedSpacing.getText().toString()));
        newPlant.setPlantNotes(plantNotes.getText().toString());
        newPlant.setHarvestDay(Float.parseFloat(harvestDay.getText().toString()));

        garden.addPlant(newPlant);

    }

    public void onClickPlantResetBtn(View v){
        plantName.setText("");
        sowDepth.setText("");
        seedSpacing.setText("");
        rowSpacing.setText("");
        harvestDay.setText("");
        plantNotes.setText("");
    }
}
