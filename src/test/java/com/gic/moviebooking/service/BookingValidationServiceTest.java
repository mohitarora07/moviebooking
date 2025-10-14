package com.gic.moviebooking.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingValidationServiceTest {
    @Test
    public void validateTicketEntryTest(){
        assertTrue(BookingValidationService.validateAndParseTicketEntry("1", 10) == 1);
    }

}
