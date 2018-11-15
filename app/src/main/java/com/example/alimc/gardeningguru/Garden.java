package com.example.alimc.gardeningguru;

import java.util.Date;
import java.util.Map;

public class Garden {

    private String name;
    private Zone zone;
    private Map<String, Plant> plants;
    private Map<String, Planting> plantings;
    private Map<Date, Task> tasksPending;

    public Garden(String name) {
        this.name = name;
        zone = new Zone("");
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Map<String, Plant> getPlants() {
        return plants;
    }

    public void setPlants(Map<String, Plant> plants) {
        this.plants = plants;
    }

    public void addPlant(Plant plant) {
        //todo
    }

    public void removePlant(String name) {
        //todo
    }

    public Map<String, Planting> getPlantings() {
        return plantings;
    }

    public void setPlantings(Map<String, Planting> plantings) {
        this.plantings = plantings;
    }

    //adds one planting from plantings
    public void addPlanting(Planting planting) {
        //todo
    }

    //removes one planting from plantings, by searching for the planting name that is passed in
    public void removePlanting(String name) {
        //todo
        //it would probably be better to just pass in a reference to the object...
    }

    public Map<Date, Task> getTasksPending() {
        return tasksPending;
    }

    public void setTasksPending(Map<Date, Task> tasksPending) {
        this.tasksPending = tasksPending;
    }

}
