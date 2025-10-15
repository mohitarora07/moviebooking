package com.gic.moviebooking.validator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BookingValidationServiceTest {
    @Test
    public void validateTicketEntryTest(){
        assert(BookingValidationService.validateAndParseTicketEntry("1", 10) == 1);
    }

    @Test
    public void validateTicketEntryInvalidTest(){
        assert(BookingValidationService.validateAndParseTicketEntry("0", 10) == -1);
        assert(BookingValidationService.validateAndParseTicketEntry("-1", 10) == -1);
        assert(BookingValidationService.validateAndParseTicketEntry("11", 10) == -1);
        assert(BookingValidationService.validateAndParseTicketEntry("abc", 10) == -1);
    }

    @Test
    public void validateSeatEntryTest() {
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("A1", 10, 10),
                new int[]{0, 0}
        ));
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("B5", 10, 10),
                new int[]{1, 4}
        ));

    }

    @Test
    public void validateSeatEntryInvalidTest() {
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("A0", 10, 10),
                new int[]{-1, -1}
        ));
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("A11", 10, 10),
                new int[]{-1, -1}
        ));
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("K1", 10, 10),
                new int[]{-1, -1}
        ));
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("AA1", 10, 10),
                new int[]{-1, -1}
        ));
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("A", 10, 10),
                new int[]{-1, -1}
        ));
        assert(Arrays.equals(
                BookingValidationService.validateStartSeat("1A", 10, 10),
                new int[]{-1, -1}
        ));
    }

}
