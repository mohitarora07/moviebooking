package com.gic.moviebooking.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PreMenuValidatorTest {
    @Test
    public void testValidInput() {
        String[] input = {"Inception", "10", "15"};
        assertTrue(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_TooFewArguments() {
        String[] input = {"Inception", "10"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_TooManyArguments() {
        String[] input = {"Inception", "10", "15", "Extra"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_NonIntegerRows() {
        String[] input = {"Inception", "Ten", "15"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_NonIntegerSeatsPerRow() {
        String[] input = {"Inception", "10", "Fifteen"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_NegativeRows() {
        String[] input = {"Inception", "-5", "15"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_ZeroSeatsPerRow() {
        String[] input = {"Inception", "10", "0"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_ExceedingMaxRows() {
        String[] input = {"Inception", "25", "15"};
        assertFalse(PreMenuValidator.validateInput(input));
    }

    @Test
    public void testInvalidInput_ExceedingMaxSeatsPerRow() {
        String[] input = {"Inception", "10", "60"};
        assertFalse(PreMenuValidator.validateInput(input));
    }
}