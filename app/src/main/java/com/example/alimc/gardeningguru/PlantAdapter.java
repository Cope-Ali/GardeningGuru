package com.example.alimc.gardeningguru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * PlantAdapter creates an ArrayAdapter type to display the different Plant objects
 *
 */

public class PlantAdapter extends ArrayAdapter<Plant> {

    /**
     * PlantAdapter
     * @param context the context we are working in
     * @param plants a list of plant objects from the Garden
     */

    public PlantAdapter(Context context, List<Plant> plants){
        super(context, 0, plants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Plant plant = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.plant_list_layout, parent, false);
        }

        TextView plantName = (TextView) convertView.findViewById(R.id.plantNameTxtV);
        plantName.setText(plant.getName());

        return convertView;
    }
}
