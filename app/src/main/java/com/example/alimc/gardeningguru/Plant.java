package com.example.alimc.gardeningguru;

public class Plant {

    private String name;
    private Float sowDepth;
    private Float seedSpacing;
    private Float rowSpacing;
    private String plantNotes;


    public Plant(){
        //default constructor
    }

    public Plant(String name){
        this.name = name;
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



}