package com.gic.moviebooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
    private int seatRowIndex;
    private BookingStatus bookingStatus;
    private String bookingId;

    public Seat(int seatRowIndex) {
        this.seatRowIndex = seatRowIndex;
        this.bookingStatus = BookingStatus.AVAILABLE;
        this.bookingId = null;
    }

    public boolean isAvailable() {
        return this.bookingStatus == BookingStatus.AVAILABLE;
    }

    public void bookSeat(String bookingId) {
        this.bookingStatus = BookingStatus.BOOKED;
        this.bookingId = bookingId;
    }

    public void reserveSeat() {
        this.bookingStatus = BookingStatus.RESERVED;
    }

    public void freeUpSeat() {
        this.bookingStatus = BookingStatus.AVAILABLE;
    }
}
