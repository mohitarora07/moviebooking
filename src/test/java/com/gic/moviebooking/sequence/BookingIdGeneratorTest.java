package com.gic.moviebooking.sequence;

import org.junit.jupiter.api.Test;

public class BookingIdGeneratorTest {

    @Test
    public void testGenerateBookingId() {
        String id1 = com.gic.moviebooking.sequence.BookingIdGenerator.generateBookingId();
        String id2 = com.gic.moviebooking.sequence.BookingIdGenerator.generateBookingId();
        String id3 = com.gic.moviebooking.sequence.BookingIdGenerator.generateBookingId();

        assert id1.equals("GIC0001");
        assert id2.equals("GIC0002");
        assert id3.equals("GIC0003");
    }
}
