package com.gic.moviebooking.service;

import com.gic.moviebooking.constants.MenuConstants;
import com.gic.moviebooking.model.Seat;
import com.gic.moviebooking.sequence.BookingIdGenerator;
import com.gic.moviebooking.validator.BookingValidationService;
import com.gic.moviebooking.validator.PreMenuValidator;

import java.util.List;
import java.util.Scanner;

import static com.gic.moviebooking.constants.MenuConstants.*;

public class MenuService {
    public static final Scanner scanner = new Scanner(System.in);
    private static BookingService bookingService;

    public static void startService() {
        preMenu();
        mainMenu();
    }

    private static void preMenu() {
        while (true) {
            System.out.println(MenuConstants.PRE_MENU);
            String[] input = scanner.nextLine().trim().split(" ");

            if (!PreMenuValidator.validateInput(input))
                continue;

            bookingService = new BookingService(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            break;
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println(String.format(MenuConstants.MAIN_MENU, bookingService.getMovieTitle(), bookingService.getTotalAvailableSeats()));

            String input = scanner.nextLine().trim();
            int menuOption;
            try{
                menuOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_OPTION);
                continue;
            }
            switch (menuOption) {
                case 1 -> ticketEntryMenu();
                case 2 -> checkBookingMenu();
                case 3 -> {
                    System.out.println(EXIT_MESSAGE);
                    System.exit(0);
                }
                default -> System.out.println(INVALID_OPTION);
            }
        }
    }

    private static void checkBookingMenu() {
        while(true){
            System.out.println(ENTER_BOOKING_ID);
            String bookingId = scanner.nextLine().trim();
            if (bookingId.isEmpty())
                mainMenu();

            SeatMapViewer.displaySeatMap(bookingService.getSeatRowList(), bookingId);
        }
    }

    private static void ticketEntryMenu() {
        while (true) {
            System.out.println(TICKET_INPUT);

            String ticketInput = scanner.nextLine();

            if (ticketInput.isBlank()) // return to main menu
                mainMenu();

            int ticketsToBook = BookingValidationService.validateAndParseTicketEntry(ticketInput, bookingService.getTotalAvailableSeats());
            if (ticketsToBook < 0)
                continue;

            seatSelectionMenu(ticketsToBook);
        }

    }

    private static void seatSelectionMenu(int ticketsToBook) {
        String bookingId = BookingIdGenerator.generateBookingId();
        List<Seat> reservedSeats = bookingService.reserveDefaultSeats(ticketsToBook);
        while(true){
            System.out.println(String.format(BOOKING_ID, bookingId));
            System.out.println(SEATS_SELECTED);
            SeatMapViewer.displaySeatMap(bookingService.getSeatRowList(), "");

            System.out.println(CONFIRM_BOOKING);
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                int[] seatCoordinates = BookingValidationService.validateStartSeat(input, bookingService.getRows(), bookingService.getSeatsPerRow());
                if (seatCoordinates[0] == -1) {
                    System.out.println(INVALID_START_SEAT);
                    continue;
                }
                bookingService.freeUpReservedSeats(reservedSeats);
               reservedSeats = bookingService.reserveSeats(ticketsToBook, seatCoordinates);
            }else{
                bookingService.bookReservedSeats(bookingId, reservedSeats);
                System.out.println(String.format(BOOKING_CONFIRMED, bookingId));
                mainMenu();
            }
    }
}
}
