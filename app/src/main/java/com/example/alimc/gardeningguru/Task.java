package com.example.alimc.gardeningguru;

import java.util.Date;

public class Task {
    private String name;
    private String plantingName;
    private Date dueDate;
    private boolean done;
    private String description;

    public Task(String name) {
        this.name = name;
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
}
