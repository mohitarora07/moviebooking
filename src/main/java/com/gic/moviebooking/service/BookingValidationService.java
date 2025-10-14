package com.gic.moviebooking.service;

import org.apache.commons.lang3.math.NumberUtils;

public class BookingValidationService {

    public static int validateAndParseTicketEntry(String ticketsToBookString, int totalAvailableSeats) {
        if(!NumberUtils.isParsable(ticketsToBookString)){
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }

        int ticketsToBook = Integer.parseInt(ticketsToBookString);
        if(ticketsToBook < 1){
            System.out.println("Number of tickets should be at least 1. Please try again.");
            return -1;
        }
        if(ticketsToBook > totalAvailableSeats){
            System.out.println("Not enough available seats. Please try again.");
            return -1;
        }
        return ticketsToBook;
    }

    public static int[] validateStartSeat(String selectedStartSeat, int rows, int seatsPerRow) {
        int[] seatCoordinates = {-1, -1};
        if (selectedStartSeat.length() < 2) return seatCoordinates;

        char rowChar = selectedStartSeat.charAt(0);
        int rowIndex = rowChar - 'A'; // Convert row letter to index (0-based from bottom)
        if (rowIndex < 0 || rowIndex >= rows) return seatCoordinates;

        String seatNumberStr = selectedStartSeat.substring(1);
        int seatNumber;
        try {
            seatNumber = Integer.parseInt(seatNumberStr);
        } catch (NumberFormatException e) {
            return seatCoordinates; // Not a valid number
        }
        if (seatNumber < 1 || seatNumber > seatsPerRow)
            return seatCoordinates;

        int seatIndex = seatNumber - 1;
        seatCoordinates[0] =  rowIndex;
        seatCoordinates[1] = seatIndex;
        return seatCoordinates;
    }
}
