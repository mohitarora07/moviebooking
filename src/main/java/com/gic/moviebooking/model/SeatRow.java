package com.gic.moviebooking.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SeatRow {
    private int seatRowIndex;
    private List<Seat> seats;
    private int availableSeatsInRow;

    public SeatRow(int seatRowIndex, int seatsInRow) {
        this.seatRowIndex = seatRowIndex;
        this.availableSeatsInRow = seatsInRow;
        this.seats = new ArrayList<>();
        for (int i = 0; i < seatsInRow; i++) {
            seats.add(new Seat(seatRowIndex));
        }
    }

    public void incrementAvailableSeatsInRow() {
        this.availableSeatsInRow++;
    }

    public void decrementAvailableSeatsInRow(int seatsReserved) {
        this.availableSeatsInRow -= seatsReserved;
    }
}
