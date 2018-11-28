package com.example.alimc.gardeningguru;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Zone contains everything you want to know about a USDA zone.
 */
public class Zone {

    private String zip;
    private String USDAcode;
    private String tempRange;

    /**
     * Constructor that takes a zip code.
     * @param zip the 5 digit US zip code used to look up zone information.
     */
    public Zone(String zip) {
        this.zip = zip;
    }

    /**
     * Default constructor sets all parameters to an empty string.
     */
    public Zone () {zip = ""; USDAcode = ""; tempRange = ""; }

    /**
     * Checks if the zip code is a valid US zip code.
     * @param zip the zip code to check.
     * @return boolean.
     */
    public boolean isValidZip(String zip){
        //String match = "";

        // Pattern to find code
        String pattern = "[0-9]{5}";  // Sequence of 5 digits
        Pattern regEx = Pattern.compile(pattern);
    //TODO add check for zip range between 00501 and 99950
        // Find instance of pattern matches
        Matcher m = regEx.matcher(zip);
        /*if (m.find()) {
            match = m.group(0);
        }*/
        return m.find();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUSDAcode() {
        return USDAcode;
    }

    public void setUSDAcode(String USDAcode) {
        this.USDAcode = USDAcode;
    }

    public String getTempRange() {
        return tempRange;
    }

    public void setTempRange(String tempRange) {
        this.tempRange = tempRange;
    }
}
