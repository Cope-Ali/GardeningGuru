package com.example.alimc.gardeningguru;

import java.util.Date;

public class Garden {

    private String name;
    private Zone zone;
/* move these member variables out of the block comment as the objects are created

    private Map<String, Plant> plants;
    private Map<String, Plantings> plantings;
    private Map<Date, Task> tasksPending;
    private Map<Date, Task> tasksComplete;
 */

    public Garden(String name) {this.name = name; }



    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
/* move these functions out of the block comment as the objects are created.


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

    public Map<String, Plantings> getPlantings() {
        return plantings;
    }

    public void setPlantings(Map<String, Plantings> plantings) {
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

    //add a single task to tasksPending
    public void addNewTask(Task task) {
     //todo
    }

    //moves a task from tasksPending, to tasksComplete
    public void completeTask(Task task) {
        //todo
    }


    //looks through all of the plantings to see if any tasks need to be removed,updated,created
    //looks through all of the the tasks to see if any need to be updated, for example, if a date has passed
    public void computeTasks() {
        //todo
    }

    public Map<Date, Task> getTasksComplete() {
        return tasksComplete;
    }

    public void setTasksComplete(Map<Date, Task> tasksComplete) {
        this.tasksComplete = tasksComplete;
    }

 */





}
