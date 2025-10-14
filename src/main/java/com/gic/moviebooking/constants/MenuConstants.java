package com.gic.moviebooking.constants;

public class MenuConstants {
    public static final String PRE_MENU = "Please define movie title and seating map in [Title] [Rows] [SeatsPerRow] format :";
    public static final String MAIN_MENU = "Welcome to GIC Cinemas\n" +
            "[1] Book Tickets for %s (%d seats available)\n" +
            "[2] Check Bookings\n" +
            "[3] Exit\n" +
            "Please enter your selection:";

    public static final String EXIT_MESSAGE = "Thank you for using GIC Cinemas booking service! Bye";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";
    public static final String TICKET_INPUT = "Enter the number of tickets to book, or enter blank to return to the main menu:";
    public static final String INSUFFICIENT_TICKETS = "Sorry, there are only %s seats available.";

    public static final String INVALID_ROWS = "Rows should be between %s and %s.";
    public static final String INVALID_SEATS_PER_ROW = "Seats per row should be between %s and %s.";
//    Seat Map
    public static final String SEAT_MAP_LEGEND = "Legend: [.] Available  [#] Booked  [o] Selected";
    public static final String SCREEN_BANNER = "S C R E E N";
    public static final String SEAT_MAP_PADDING = "  ";

    public static final String BOOKING_ID = "Booking ID: %s";
    public static final String SEAT_SELECTION_PROMPT = "Select a start seat e.g (B03):\n";
    public static final String INVALID_START_SEAT = "Invalid start seat selection. Please try again.";

    public static final String BOOKING_CONFIRMED = "Booking id: %s confirmed.";
    public static final String CONFIRM_BOOKING = "Enter blank to accept seat selection or enter a new seating position:";

    public static final String ENTER_BOOKING_ID = "Enter booking id, or enter blank to go back to the main menu:";
    public static final String SEATS_SELECTED = "Selected Seats:\n";
}
