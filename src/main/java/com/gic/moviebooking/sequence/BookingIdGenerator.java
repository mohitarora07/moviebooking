package com.gic.moviebooking.sequence;

public class BookingIdGenerator {
    private static int counter = 0;

    public static String generateBookingId() {
        counter++;
        return "GIC" + String.format("%04d", counter);
    }
}
