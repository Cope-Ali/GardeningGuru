package com.example.alimc.gardeningguru;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Everything you want to know about a Garden, including it's name, the zone, plants, plantings, and tasks.
 */
public class Garden {

    private String name;
    private Zone zone;
    private Map<String, Plant> plants;
    private Map<String, Planting> plantings;
    private Map<Date, Task> tasksPending;

    /**
     * Default constructor for Garden.
     */
    public Garden() {
        name = "";
        zone = new Zone();
        plants = new HashMap<>();
        plantings = new HashMap<>();
        tasksPending = new TreeMap<>();
    }

    /**
     * Constructor for Garden that takes a name.
     * @param name the name of the garden as a string.
     */
    public Garden(String name) {
        this.name = name;
        zone = new Zone();
        plants = new HashMap<>();
        plantings = new HashMap<>();
        tasksPending = new TreeMap<>();
    }

    /**
     * Saves the garden to SharedPreferences
     */
    public void saveGarden() {
        SharedPreferences.Editor prefsEditor = MainActivity.mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        prefsEditor.putString("garden", json);
        prefsEditor.apply();
    }

    /**
     * Load the garden from SharedPreferences, and overwrite this garden's members with the loaded information.
     */
    public void loadGarden() {
        Garden newGarden;
        Gson gson = new Gson();
        String json = MainActivity.mPrefs.getString("garden", "");
        newGarden = gson.fromJson(json, Garden.class);
        this.name = newGarden.getName();
        this.zone = newGarden.getZone();
        this.plants = newGarden.getPlants();
        this.plantings = newGarden.getPlantings();
        this.tasksPending = newGarden.getTasksPending();
    }

    public Zone getZone() {

        return zone;
    }

    public void setZone(Zone zone) {

        this.zone = zone;
    }

    public Map<String, Plant> getPlants() {

        return this.plants;
    }

    public Plant getPlant(String key){

       return this.plants.get(key);
    }

    public void setPlants(Map<String, Plant> plants) {

        this.plants = plants;
    }

    public void addPlant(Plant plant) {
        //This was added to test addPlant activity
        if(this.plants == null)
        {
            this.plants = new HashMap<>();
        }

        this.plants.put(plant.getName(), plant);
    }

    public void removePlant(String name) {

        this.plants.remove(name);
    }

    public Map<String, Planting> getPlantings() {
        return plantings;
    }

    public void setPlantings(Map<String, Planting> plantings) {
        this.plantings = plantings;
    }

    //adds one planting from plantings
    public void addPlanting(Planting planting) {
        plantings.put(planting.getName(), planting);
    }

    //removes one planting from plantings, by searching for the planting name that is passed in
    public void removePlanting(String name) {
        plantings.remove(name);
    }

    public Map<Date, Task> getTasksPending() {
        return tasksPending;
    }

    public void setTasksPending(Map<Date, Task> tasksPending) {
        this.tasksPending = tasksPending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
