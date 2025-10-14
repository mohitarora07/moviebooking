package com.gic.moviebooking.validator;

import static com.gic.moviebooking.constants.MenuConstants.INVALID_ROWS;
import static com.gic.moviebooking.constants.MenuConstants.INVALID_SEATS_PER_ROW;

public class PreMenuValidator {

    private static final int MINIMUM_ROWS = 5;
    private static final int MAXIMUM_ROWS = 20;
    private static final int MINIMUM_SEATS_PER_ROW = 5;
    private static final int MAXIMUM_SEATS_PER_ROW = 25;

    public static boolean validateInput(String[] input) {
        if (input.length != 3) {
            System.out.println("Invalid number of arguments");
            return false;
        }

        try {
            int rows = Integer.parseInt(input[1]);
            int seatsPerRow = Integer.parseInt(input[2]);
            if (rows < MINIMUM_ROWS || rows > MAXIMUM_ROWS) {
                System.out.println(String.format(INVALID_ROWS, MINIMUM_ROWS, MAXIMUM_ROWS));
                return false;
            }
            if (seatsPerRow < MINIMUM_SEATS_PER_ROW || seatsPerRow > MAXIMUM_SEATS_PER_ROW) {
                System.out.println(String.format(INVALID_SEATS_PER_ROW, MINIMUM_SEATS_PER_ROW, MAXIMUM_SEATS_PER_ROW));
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Rows and Seats per row should be numeric values");
            return false;
        }

        return true;
    }
}
