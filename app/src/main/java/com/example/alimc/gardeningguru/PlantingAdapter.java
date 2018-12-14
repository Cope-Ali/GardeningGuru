package com.example.alimc.gardeningguru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * PlantingAdapter extends ArrayAdaopter to create a way to display Planting objects.
 */

class PlantingAdapter extends ArrayAdapter<Planting> {

    /**
     * PlantingAdapter
     * @param context the context we are working in
     * @param plantings a list of planting objects from the garden we need to display
     */

    public PlantingAdapter(Context context, List<Planting> plantings){
        super(context, 0, plantings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Planting planting = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.planting_list_layout, parent, false);
        }

        TextView plantingName = (TextView) convertView.findViewById(R.id.plantingNameTxtV);
        plantingName.setText(planting.getName());

        return convertView;
    }

}
