package com.example.alimc.gardeningguru;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Planting {
    private String name;
    private Plant plant;
    private Date plantWhen;
    private boolean planted;
    private int weedingInterval;
    private int daysTillHarvest;
    private int germinationDays;
    private int daysTillThin;
    private String notes;
    private String location;
    private Map<String, Task> tasksPending;
    private Map<String, Task> tasksComplete;
    public static final int DEFAULT_WEEDING_INTERVAL = 3; //todo look up weeding schedule by USDA zone


    public Planting(String name, Plant plant) {
        this.name = name;
        this.plant = plant;
        plantWhen = new Date();
        planted = false;
        weedingInterval = DEFAULT_WEEDING_INTERVAL;
        tasksPending = new HashMap<>();
        tasksComplete = new HashMap<>();
        //todo do any other member variables need to be set here?
    }

    public Date computeWeedingDate() {
        //todo this should compute the next weeding date
        return new Date();
    }

    public void computeTasks() {
        //todo this should check if any tasks need to be added to tasksPending
    }

    public void addNewTask(String taskName, Task task) {
        tasksPending.put(taskName, task);
    }

    public void completeTask(String taskName) {
        if (tasksPending.containsKey(taskName)) {
            tasksPending.get(taskName).setDone(true);
            tasksComplete.put(taskName, tasksPending.get(taskName));
            tasksPending.remove(taskName);
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
        return tasksPending;
    }

    public void setTasksPending(Map<String, Task> tasksPending) {
        this.tasksPending = tasksPending;
    }

    public Map<String, Task> getTasksComplete() {
        return tasksComplete;
    }

    public void setTasksComplete(Map<String, Task> tasksComplete) {
        this.tasksComplete = tasksComplete;
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
