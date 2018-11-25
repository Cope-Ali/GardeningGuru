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
    private boolean harvested;
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

    /**
     *  computeTasks
     *  Puts any needed tasks into "tasks", and makes any needed updates.
     */
    public void computeTasks() {

        //Plant task...
        //Skip if already planted, or task already exists.
        String plantTaskName = name + "_plant";
        if (!planted && !tasks.containsKey(plantTaskName)) {
            Task plantTask = new Task(plantTaskName, plant.getName(), plantWhen
                    , false, "", 0);
            tasks.put(plantTaskName, plantTask);
        } else {
            if (isHarvested()) {
                tasks.get(plantTaskName).isDone();
            } else if (!tasks.get(plantTaskName).isDone()
             && tasks.get(plantTaskName).getDueDate().before(new Date())) {
                tasks.get(plantTaskName).setDueDate(new Date());
                tasks.get(plantTaskName).incrementRescheduled();
            }
        }

        //Weeding task...
        String weedingTaskName = name + "_weeding";
        //Create weeding task if needed. If it already exists, then update it.
        if (!tasks.containsKey(weedingTaskName)) {
            Task weedingTask = new Task(weedingTaskName, plant.getName(), computeWeedingDate()
                    , false, "", 0);
            tasks.put(plantTaskName, weedingTask);
        } else {
            if (isHarvested()) {
                tasks.get(weedingTaskName).isDone();
            } else if (tasks.get(weedingTaskName).getDueDate().before(new Date())) {
                tasks.get(weedingTaskName).setDueDate(computeWeedingDate());
                tasks.get(weedingTaskName).setDone(false);
                tasks.get(weedingTaskName).incrementRescheduled();
            }
        }

        //Harvest task...
        String harvestTaskName = name + "_harvest";
        if (!tasks.containsKey(harvestTaskName)) {
            Task harvestTask = new Task(harvestTaskName, plant.getName(), computeHarvestDate()
                    , false, "", 0);
            tasks.put(harvestTaskName, harvestTask);
        } else {
            if (isHarvested()) {
                tasks.get(harvestTaskName).isDone();
            } else if (tasks.get(harvestTaskName).isDone()) {
                setHarvested(true);
            } else if (tasks.get(harvestTaskName).getDueDate().before(new Date())) {
                tasks.get(harvestTaskName).setDueDate(new Date());
                tasks.get(harvestTaskName).incrementRescheduled();
            }
        }

        //Thinning task...
        String thinTaskName = name + "_thin";
        if (!tasks.containsKey(thinTaskName)) {
            Task thinTask = new Task(thinTaskName, plant.getName(), computeThinDate()
                    , false, "", 0);
            tasks.put(plantTaskName, thinTask);
        } else {
            if (isHarvested()) {
                tasks.get(thinTaskName).isDone();
            } else if (!tasks.get(thinTaskName).isDone()
                    && tasks.get(thinTaskName).getDueDate().before(new Date())) {
                tasks.get(thinTaskName).setDueDate(new Date());
                tasks.get(thinTaskName).incrementRescheduled();
            }
        }
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

    public Date computeThinDate() {
        return addDaysToDate(plantWhen, daysTillThin);
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

    public boolean isHarvested() {
        return harvested;
    }

    public void setHarvested(boolean harvested) {
        this.harvested = harvested;
    }
}
