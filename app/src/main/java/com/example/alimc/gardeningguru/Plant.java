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
    private Float harvestDay;
    private Float germinationDay;

    /**
     * Default constructor.
     */
    public Plant(){
        name = "";
        sowDepth = (float)0;
        seedSpacing = (float)0;
        rowSpacing = (float)0;
        plantNotes = "";
        harvestDay = (float)0;
        germinationDay = (float)0;
    }

    /**
     * Constructor that takes the name of the plant as a string.
     * @param name the name of the plant as a string.
     */
    public Plant(String name){
        this.name = name;
        sowDepth = (float)0;
        seedSpacing = (float)0;
        rowSpacing = (float)0;
        plantNotes = "";
        harvestDay = (float)0;
        germinationDay = (float)0;
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

    public Float getHarvestDay(){

        return harvestDay;
    }

    public void setHarvestDay(Float harvestDay){

        this.harvestDay = harvestDay;
    }

    public Float getGerminationDay(){
        return this.germinationDay;
    }

    public void setGerminationDay(Float germinationDay){
        this.germinationDay = germinationDay;
    }


}
