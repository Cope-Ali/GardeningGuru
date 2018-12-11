package com.example.alimc.gardeningguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static android.R.layout.simple_spinner_item;

public class PlantGarden extends AppCompatActivity {
    Garden garden = MainActivity.garden;
    private Planting newPlanting;
    private PlantingAdapter adapter;
    private List<Planting> plantingArrayList;
    private ListView plantingList;
    private EditText plDate;
    private EditText plLocation;
    private TextView plantingName;
    private EditText plantingNotes;
    private Button saveEditBtn;
    private Button resetDeleteBtn;
    private TextView plantingArrayLocation;
    private Spinner spinner;
    private String selectedPlantName;
    private String createdPlantingName;
    public String[] plantNames;
    private Plant plant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load garden objects
        garden.loadGarden();

        setContentView(R.layout.activity_plant_garden);
        this.plDate = (EditText) findViewById(R.id.plantingDate);
        this.plantingName = (TextView) findViewById(R.id.plantingName);
        this.plLocation = (EditText) findViewById(R.id.plantingLocation);
        this.plantingNotes = (EditText) findViewById(R.id.plantingNotes);
        this.plantingList = (ListView) findViewById(R.id.plantingList);
        this.saveEditBtn = (Button) findViewById(R.id.plantingSaveUpdate);
        this.resetDeleteBtn = (Button) findViewById(R.id.plantingResetDeleteBtn);
        this.plantingArrayLocation = (TextView) findViewById(R.id.plantingArrayListPosition);
        this.spinner = findViewById(R.id.plantDropdown);
        this.setListView();
        List<String> items = getPlantNames();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPlantName = (String) parent.getItemAtPosition(position);
                createdPlantingName = selectedPlantName;
                if(plDate !=null)
                    createdPlantingName += plDate;
                if(plLocation !=null)
                    createdPlantingName += plLocation;
                setPlantingName();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setPlantingName() {
        this.plantingName.setText(createdPlantingName);
    }


    private List<String> getPlantNames(){
        //cycle through all plants in garden.
        List<String> plantNames = new ArrayList<String>();
        int i = 0;
        for (Map.Entry<String, Plant> plant : garden.getPlants().entrySet()) {
            plantNames.add(plant.getKey());
        }
        return plantNames;
    }
    


        private void setListView() {
        Collection<Planting> values;

        if(garden.getPlantings() != null){
            values = garden.getPlantings().values();
            this.plantingArrayList = new ArrayList<Planting>(values);
            this.adapter = new PlantingAdapter (this, plantingArrayList);
            this.plantingList.setAdapter(adapter);
            this.plantingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onPlantingItemClick(position);
            }
        });
        }
        else {
            List<String> noPlantingList = new ArrayList<>();
            noPlantingList.add("No Plantings Added");
            ArrayAdapter noPlantingAdapter = new ArrayAdapter(this, R.layout.no_plant_list_layout, noPlantingList);
            this.plantingList.setAdapter(noPlantingAdapter);
        }

    }

    public void onClickPlantSaveBtn(View v){

        String buttonText = this.saveEditBtn.getText().toString();

        if(buttonText.equals(getResources().getString(R.string.add_plant))) {
            this.newPlanting = new Planting();
            this.inputToPlanting(this.newPlanting);
            garden.addPlanting(this.newPlanting);
            this.setListView();
            this.clearInputFields();
        }
        else if(buttonText.equals(getResources().getString(R.string.update_planting))){
            this.inputToPlanting(this.adapter.getItem(Integer.parseInt(this.plantingArrayLocation.getText().toString())));
            this.clearInputFields();
            this.adapter.notifyDataSetChanged();
            this.setButtonText("add");
        }
        garden.saveGarden();
    }

    private void setButtonText(String action) {
        if(action.equals("edit")){
            this.resetDeleteBtn.setText(getResources().getString(R.string.delete_planting));
            this.saveEditBtn.setText(getResources().getString(R.string.update_planting));
        }
        else if(action.equals("add")){
            this.resetDeleteBtn.setText(getResources().getString(R.string.reset_planting));
            this.saveEditBtn.setText(getResources().getString(R.string.add_planting));
        }
    }

    private void clearInputFields() {
        this.plantingName.setText("");
        this.plLocation.setText("");
        this.plantingNotes.setText("");
        this.plDate.setText("");
    }

    private void inputToPlanting(Planting newPlanting) {
        newPlanting.setName(this.plantingName.getText().toString());
        //TODO fix plant as string into plant as object
       // newPlanting.setPlant(this.plant.getText().toString());
        //TODO figure out how to set date
       // newPlanting.setPlantWhen(this.plDate.getText().toDate());
        newPlanting.setNotes(this.plantingNotes.getText().toString());
    }

    public void plantingToInput(Planting planting){
        this.plantingName.setText(planting.getName());
        //this.plant.setPlant(planting.getPlant().toString());
        this.plLocation.setText(planting.getLocation());
        this.plantingNotes.setText(planting.getNotes());
        this.plDate.setText(planting.getPlantWhen().toString());
    }

    private void onPlantingItemClick(int position) {
        this.plantingToInput(this.adapter.getItem(position));
        this.plantingArrayLocation.setText(Integer.toString(position));
        this.setButtonText("edit");
    }

    public void onClickPlantingResetBtn(View v) {
        String buttonText = this.resetDeleteBtn.getText().toString();
        if (buttonText.equals(getResources().getString(R.string.reset_plant))) {
            this.clearPlantingInput();
        } else if (buttonText.equals(getResources().getString(R.string.delete_planting))) {
            int position = Integer.parseInt(this.plantingArrayLocation.getText().toString());
            Planting plantingObj = this.adapter.getItem(position);
            this.plantingArrayList.remove(position);
            garden.removePlanting(plantingObj.getName());
            this.adapter.notifyDataSetChanged();
            this.clearPlantingInput();
            this.setButtonText("add");
        }
    }

    private void clearPlantingInput() {
        this.plantingName.setText("");
        this.plantingNotes.setText("");
        this.plDate.setText("");
        //this.plant.setText("");
    }
}
