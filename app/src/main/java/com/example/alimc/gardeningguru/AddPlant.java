package com.example.alimc.gardeningguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.alimc.gardeningguru.MainActivity.garden;

public class AddPlant extends AppCompatActivity {

    private Plant newPlant;
    private PlantAdapter adapter;
    private List<Plant> plantsArrayList;
    private EditText plantName;
    private EditText sowDepth;
    private EditText seedSpacing;
    private EditText rowSpacing;
    private EditText harvestDay;
    private EditText plantNotes;
    private ListView plantList;
    private EditText germinationDay;
    private TextView plantArrayLocation;
    private Button saveEditBtn;
    private Button resetDeleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        this.plantName = (EditText) findViewById(R.id.name);
        this.sowDepth = (EditText) findViewById(R.id.sowDepth);
        this.seedSpacing = (EditText) findViewById(R.id.seedSpacing);
        this.rowSpacing = (EditText) findViewById(R.id.rowSpacing);
        this.harvestDay = (EditText) findViewById(R.id.harvestDays);
        this.plantNotes = (EditText) findViewById(R.id.plantNotes);
        this.plantList = (ListView) findViewById(R.id.plantList);
        this.germinationDay = (EditText) findViewById(R.id.germinationDays);
        this.plantArrayLocation = (TextView) findViewById(R.id.plantArrayListPosition);
        this.saveEditBtn = (Button) findViewById(R.id.savePlant);
        this.resetDeleteBtn = (Button) findViewById(R.id.resetPlant);
        this.setListView();

    }

    public void setListView(){

        Collection<Plant> values;

        if(garden.getPlants() != null){
            values = garden.getPlants().values();
            this.plantsArrayList = new ArrayList<Plant>(values);
            this.adapter = new PlantAdapter(this,plantsArrayList);
            this.plantList.setAdapter(adapter);
            this.plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onPlantItemClick(position);
                }
            });
        }
        else{
            List<String> noPlantList = new ArrayList<>();
            noPlantList.add("No Plants added");
            ArrayAdapter noPlantAddapter = new ArrayAdapter(this,R.layout.no_plant_list_layout,noPlantList);
            this.plantList.setAdapter(noPlantAddapter);
        }


    }


    public void onClickPlantSaveBtn(View v){

        String buttonText = this.saveEditBtn.getText().toString();

        if(buttonText.equals(getResources().getString(R.string.add_plant))) {
            this.newPlant = new Plant();
            this.inputToPlant(this.newPlant);
            garden.addPlant(this.newPlant);
            this.setListView();
            this.clearInputFields();
        }
        else if(buttonText.equals(getResources().getString(R.string.update_plant))){
            this.inputToPlant(this.adapter.getItem(Integer.parseInt(this.plantArrayLocation.getText().toString())));
            this.clearInputFields();
            this.setButtonText("add");
        }

    }


    public void inputToPlant(Plant plant){
        plant.setName(this.plantName.getText().toString());
        plant.setSowDepth(Float.parseFloat(this.sowDepth.getText().toString()));
        plant.setRowSpacing(Float.parseFloat(this.rowSpacing.getText().toString()));
        plant.setSeedSpacing(Float.parseFloat(this.seedSpacing.getText().toString()));
        plant.setPlantNotes(this.plantNotes.getText().toString());
        plant.setHarvestDay(Float.parseFloat(this.harvestDay.getText().toString()));
        plant.setGerminationDay(Float.parseFloat(this.germinationDay.getText().toString()));
    }


    public void plantToInput(Plant plant){
        this.plantName.setText(plant.getName());
        this.sowDepth.setText(plant.getSowDepth().toString());
        this.seedSpacing.setText(plant.getSeedSpacing().toString());
        this.rowSpacing.setText(plant.getRowSpacing().toString());
        this.harvestDay.setText(plant.getHarvestDay().toString());
        this.plantNotes.setText(plant.getPlantNotes());
        this.germinationDay.setText(plant.getGerminationDay().toString());
    }

    public void onPlantItemClick(int position){
        this.plantToInput(this.adapter.getItem(position));
        this.plantArrayLocation.setText(Integer.toString(position));
        this.setButtonText("edit");
    }

    public void onClickPlantResetBtn(View v){

        String buttonText = this.resetDeleteBtn.getText().toString();
        if(buttonText.equals(getResources().getString(R.string.reset_plant))) {
            this.clearInputFields();
        }
        else if(buttonText.equals(getResources().getString(R.string.delete_plant))){
            int position = Integer.parseInt(this.plantArrayLocation.getText().toString());
            Plant plantObj = this.adapter.getItem(position);
            this.plantsArrayList.remove(position);
            garden.removePlant(plantObj.getName());
            this.adapter.notifyDataSetChanged();
            this.clearInputFields();
            this.setButtonText("add");
        }

    }

    public void clearInputFields(){
        this.plantName.setText("");
        this.sowDepth.setText("");
        this.seedSpacing.setText("");
        this.rowSpacing.setText("");
        this.harvestDay.setText("");
        this.plantNotes.setText("");
        this.plantArrayLocation.setText("");
        this.germinationDay.setText("");
    }

    public void setButtonText(String action){

        if(action.equals("edit")){
            this.resetDeleteBtn.setText(getResources().getString(R.string.delete_plant));
            this.saveEditBtn.setText(getResources().getString(R.string.update_plant));
        }
        else if(action.equals("add")){
            this.resetDeleteBtn.setText(getResources().getString(R.string.reset_plant));
            this.saveEditBtn.setText(getResources().getString(R.string.add_plant));
        }
    }
}
