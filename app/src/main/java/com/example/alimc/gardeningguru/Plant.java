package com.example.alimc.gardeningguru;

/**
 * Everything you want to know about a Plant.
 */
public class Plant {

    private String name;
    private Float sowDepth;
    private Float seedSpacing;
    private Float rowSpacing;
    private String plantNotes;
    private int daysTillHarvest;
    private int daysTillGermination;

    /**
     * Default constructor.
     */
    public Plant(){
        this.name = "";
        this.sowDepth = (float)0;
        this.seedSpacing = (float)0;
        this.rowSpacing = (float)0;
        this.plantNotes = "";
        this.daysTillHarvest = 0;
        this.daysTillGermination = 0;
    }

    /**
     * Constructor that takes the name of the plant as a string.
     * @param name the name of the plant as a string.
     */
    public Plant(String name){
        this.name = name;
        this.sowDepth = (float)0;
        this.seedSpacing = (float)0;
        this.rowSpacing = (float)0;
        this.plantNotes = "";
        this.daysTillHarvest = 0;
        this.daysTillGermination = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Float getSowDepth() {

        return sowDepth;
    }

    public void setSowDepth(Float sowDepth) {

        this.sowDepth = sowDepth;
    }

    public Float getSeedSpacing() {

        return seedSpacing;
    }

    public void setSeedSpacing(Float seedSpacing) {

        this.seedSpacing = seedSpacing;
    }

    public Float getRowSpacing() {

        return rowSpacing;
    }

    public void setRowSpacing(Float rowSpacing) {

        this.rowSpacing = rowSpacing;
    }

    public String getPlantNotes() {

        return plantNotes;
    }

    public void setPlantNotes(String plantNotes) {

        this.plantNotes = plantNotes;
    }

    public int getDaysTillHarvest(){

        return daysTillHarvest;
    }

    public void setDaysTillHarvest(int daysTillHarvest){

        this.daysTillHarvest = daysTillHarvest;
    }

    public int getDaysTillGermination(){
        return this.daysTillGermination;
    }

    public void setDaysTillGermination(int daysTillGermination){
        this.daysTillGermination = daysTillGermination;
    }


}
