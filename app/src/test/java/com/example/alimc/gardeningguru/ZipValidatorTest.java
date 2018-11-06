package com.example.alimc.gardeningguru;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ZipValidatorTest {
Zone testZone = new Zone();
private String goodZip = "96002";
private String shortZip = "8400";
private String alphaZip = "8400B";

    @Test
    public void zipValidator_CorrectZipSimple_ReturnsTrue() {
        assertTrue(testZone.isValidZip(goodZip));
        assertFalse(testZone.isValidZip(shortZip));
        assertFalse(testZone.isValidZip(alphaZip));

    }
}
