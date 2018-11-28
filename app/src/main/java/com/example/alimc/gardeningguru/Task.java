package com.example.alimc.gardeningguru;

import java.util.Date;

/**
 * Everything you want to know about tasks in the garden.
 */
public class Task {
    /** The name of the task */
    private String name;
    private String plantingName;
    private Date dueDate;
    private boolean done;
    private String description;
    private int rescheduled;

    /**
     * Constructor taking all members as parameters
     * @param name the name of the task
     * @param plantingName the name of the planting
     * @param dueDate the due date of the task
     * @param done is the task complete?
     * @param description description of the task.
     * @param rescheduled counter to see how many times the task was rescheduled.
     */
    public Task(String name
            , String plantingName
            , Date dueDate
            , boolean done
            , String description
            , int rescheduled) {
        this.name = name;
        this.plantingName = plantingName;
        this.dueDate = dueDate;
        this.done = done;
        this.description = description;
        this.rescheduled = rescheduled;
    }
    /**
     * Default Task constructor. Date gets set to today's date.
     */
    public Task() {
        name = "";
        plantingName = "";
        dueDate = new Date();
        done = false;
        description = "";
        rescheduled = 0;
    }

    /**
     * Task constructor, taking just the name.
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        plantingName = "";
        dueDate = null;
        done = false;
        description = "";
        rescheduled = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantingName() {
        return plantingName;
    }

    public void setPlantingName(String plantingName) {
        this.plantingName = plantingName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRescheduled() {
        return rescheduled;
    }

    public void setRescheduled(int rescheduled) {
        this.rescheduled = rescheduled;
    }

    /**
     * Adds 1 to the member: rescheduled.
     */
    public void incrementRescheduled() {this.rescheduled++; }
}
