package com.gic.moviebooking.service;

import com.gic.moviebooking.model.Seat;
import com.gic.moviebooking.model.SeatRow;

import java.util.List;

import static com.gic.moviebooking.constants.MenuConstants.SCREEN_BANNER;
import static com.gic.moviebooking.constants.MenuConstants.SEAT_MAP_PADDING;

public class SeatMapViewer {

    private static void printSeatNumbers(int seatsPerRow) {
        System.out.print(" " + SEAT_MAP_PADDING); // initial padding for alignment
        for (int j = 1; j <= seatsPerRow; j++) {
            System.out.print(padSeatNumber(j));
        }
        System.out.println("\n");
    }

    private static String padSeatNumber(int j) {
        return j < 10 ? j + SEAT_MAP_PADDING : j + " ";
    }

    private static char getRowLabel(int rows, int i) {
        char rowLabel = (char) ('A' + i);
        return rowLabel;
    }

    private static void screenBanner(int seatsPerRow) {
        int totalLength = seatsPerRow * 2 - 1; // each seat takes 2 spaces (seat + space) except the last one
        int paddingLength = (totalLength - SCREEN_BANNER.length());
        String paddedScreen = "-".repeat(Math.max(0, paddingLength)) + SCREEN_BANNER + "-".repeat(Math.max(0, paddingLength));
        System.out.println(paddedScreen);
    }

    public static void displaySeatMap(List<SeatRow> seatRows, String bookingId) {
        int seatsPerRow = seatRows.get(0).getSeats().size();
        screenBanner(seatsPerRow);
        seatSelectionMap(seatRows, bookingId);
        printSeatNumbers(seatsPerRow);
    }

    private static void seatSelectionMap(List<SeatRow> seatRows, String bookingId) {
        for (int i = seatRows.size() - 1; i >= 0; i--) {
            SeatRow seatRow = seatRows.get(i);
            char rowLabel = getRowLabel(seatRows.size(), i);
            System.out.print(rowLabel + SEAT_MAP_PADDING);
            for (Seat seat : seatRow.getSeats()) {
                switch(seat.getBookingStatus()) {
                    case BOOKED -> {
                        if (seat.getBookingId().equals(bookingId))
                            System.out.print("o" + SEAT_MAP_PADDING);
                        else
                            System.out.print("#" + SEAT_MAP_PADDING);
                    }
                    case RESERVED -> System.out.print("o" + SEAT_MAP_PADDING);
                    case AVAILABLE -> System.out.print("." + SEAT_MAP_PADDING);
                }
            }
            System.out.println();
        }
    }
}
