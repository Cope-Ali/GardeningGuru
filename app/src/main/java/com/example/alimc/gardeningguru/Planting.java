package com.example.alimc.gardeningguru;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Planting {
    private String name; //procedurally generated (Plant name + Date)
    private Plant plant;
    private Date plantWhen;
    private boolean planted;
    private int weedingInterval;
    private int daysTillHarvest;
    private int germinationDays;
    private int daysTillThin;
    private String notes;
    private String location;
    private Map<String, Task> tasks;
    public static final int DEFAULT_WEEDING_INTERVAL = 3; //STRETCH todo look up weeding schedule by USDA zone and/or maybe seasonally


    public Planting(String name, Plant plant) {
        this.name = name;
        this.plant = plant;
        plantWhen = new Date();
        planted = false;
        weedingInterval = DEFAULT_WEEDING_INTERVAL;
        tasks = new HashMap<>();
    }

    public Date computeWeedingDate() {
        Date currentDate = new Date(); //default constructor sets date to now
        Date weedingDate = plantWhen;
        while (currentDate.after(weedingDate)) {
            weedingDate = addDaysToDate(weedingDate, weedingInterval);
        }
        return weedingDate;
    }

    public void computeTasks() {
        //todo this should check if any tasks need to be added to tasksPending

        //set plant task, if not planted, and if there is no task
        String plantTaskName = plant.getName() + "_plant";
        if (!planted && !tasks.containsKey(plantTaskName)) {
            Task plantTask = new Task(plantTaskName, plant.getName(), plantWhen
                    , false, "", 0);
            tasks.put(plantTaskName, plantTask);
        }

        //Weeding task...
        String weedingTaskName = plant.getName() + "_weeding";
        if (!tasks.containsKey(weedingTaskName)) { //Create weeding task if needed.
            Task weedingTask = new Task(weedingTaskName, plant.getName(), computeWeedingDate()
                    , false, "", 0);
        } else { //Update weeding task if needed.
            if (!tasks.get(weedingTaskName).getDueDate().after(new Date())) {
                tasks.get(weedingTaskName).setDueDate(computeWeedingDate());
                tasks.get(weedingTaskName).setDone(false);
            }
        }

        //set task for harvest, if it doesn't exist

        //set task for thinning

        //task name = plant + task (eg weed)

        //clean up tasks
            //update due date until tomorrow, increment counter
    }

    public void addNewTask(String taskName, Task task) {
        tasks.put(taskName, task);
    }

    public void completeTask(String taskName) {
        if (tasks.containsKey(taskName)) {
            tasks.get(taskName).setDone(true);
            tasks.remove(taskName);
        } else {
            Log.d("Planting", "completeTask: " + taskName + "was not in tasksPending.");
        }

    }

    public Date computeHarvestDate() {
        return addDaysToDate(plantWhen, daysTillHarvest);
    }

    public Date computeGerminationDate() {
        return addDaysToDate(plantWhen, germinationDays);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Date getPlantWhen() {
        return plantWhen;
    }

    public void setPlantWhen(Date plantWhen) {
        this.plantWhen = plantWhen;
    }

    public boolean isPlanted() {
        return planted;
    }

    public void setPlanted(boolean planted) {
        this.planted = planted;
    }

    public int getWeedingInterval() {
        return weedingInterval;
    }

    public void setWeedingInterval(int weedingInterval) {
        this.weedingInterval = weedingInterval;
    }

    public int getDaysTillHarvest() {
        return daysTillHarvest;
    }

    public void setDaysTillHarvest(int daysTillHarvest) {
        this.daysTillHarvest = daysTillHarvest;
    }

    public int getGerminationDays() {
        return germinationDays;
    }

    public void setGerminationDays(int germinationDays) {
        this.germinationDays = germinationDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Task> getTasksPending() {
        return tasks;
    }

    public void setTasksPending(Map<String, Task> tasksPending) {
        this.tasks = tasksPending;
    }

    public int getDaysTillThin() {
        return daysTillThin;
    }

    public void setDaysTillThin(int daysTillThin) {
        this.daysTillThin = daysTillThin;
    }

    private Date addDaysToDate(Date date, int days) {
        /* https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date */
        Date datePlusDays = date;
        Calendar c = Calendar.getInstance();
        c.setTime(datePlusDays);
        c.add(Calendar.DATE, days);
        datePlusDays = c.getTime();
        return datePlusDays;
    }
}
