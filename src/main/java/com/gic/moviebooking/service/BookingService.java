package com.gic.moviebooking.service;

import com.gic.moviebooking.model.Seat;
import com.gic.moviebooking.model.SeatRow;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookingService {
    private String movieTitle;
    private final int rows;
    private final int seatsPerRow;
    private int totalAvailableSeats;
    private List<SeatRow> seatRowList;


    public BookingService(String movieTitle, int rows, int seatsPerRow) {
        this.movieTitle = movieTitle;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.totalAvailableSeats = rows * seatsPerRow;
        intializeSeats();
    }

    private void intializeSeats() {
        seatRowList = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            seatRowList.add(new SeatRow(rowIndex, seatsPerRow));
        }
    }


    public List<Seat> reserveSeats(int ticketsToReserve, int[] coordinates) {
        List<Seat> reservedSeats = reserveSeatsInSelectedRow(ticketsToReserve, coordinates);

        if (reservedSeats.size() < ticketsToReserve) {
            List<Seat> remainingSeats = overFlowingSeats(ticketsToReserve - reservedSeats.size(),
                    coordinates[0] + 1);
            reservedSeats.addAll(remainingSeats);
        }

        return reservedSeats;
    }

    public List<Seat> reserveDefaultSeats(int ticketsToReserve) {
        return overFlowingSeats(ticketsToReserve, 0);
    }

    private List<Seat> overFlowingSeats(int ticketsToReserve, int startRowIndex){
        List<Seat> reservedSeats = new ArrayList<>();
        while (totalAvailableSeats > 0 && ticketsToReserve > 0) {
            for (int i = startRowIndex; i < seatRowList.size(); i++) {
                SeatRow seatRow = seatRowList.get(i);
                if (seatRow.getAvailableSeatsInRow() == 0)
                    continue; // Skip full rows

                List<Seat> seats = reserveMiddleSeats(seatRow, ticketsToReserve);
                reservedSeats.addAll(seats);
                ticketsToReserve -= seats.size();

                if( ticketsToReserve == 0)
                    break;
            }
           startRowIndex = 0;
        }
        return reservedSeats;
    }

    private List<Seat> reserveSeatsInSelectedRow(int ticketsToReserve, int[] coordinates) {
        SeatRow seatRow = seatRowList.get(coordinates[0]);
        List<Seat> seatsInSelectedRow = seatRow.getSeats();
        List<Seat> reservedSeats = new ArrayList<>();
        for (int i = coordinates[1]; i < seatsPerRow; i++) {
            Seat seat = seatsInSelectedRow.get(i);
            if (seat.isAvailable()) {
                seat.reserveSeat();
                reservedSeats.add(seat);
            }
            if (reservedSeats.size() == ticketsToReserve)
                break;
        }
        seatRow.decrementAvailableSeatsInRow(reservedSeats.size());
        totalAvailableSeats -= reservedSeats.size();
        return reservedSeats;
    }

    private int[] findCoordinatesOfLargestFreeBlock(SeatRow seatRow) {
        if (seatRow.getAvailableSeatsInRow() == seatsPerRow) {
            return new int[]{0, seatsPerRow - 1};
        }

        int maxBlockSize = 0;
        int currentBlockSize = 0;
        int startIndex = -1;
        int bestStartIndex = -1;
        List<Seat> seats = seatRow.getSeats();
        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i).isAvailable()) {
                if (currentBlockSize == 0) {
                    startIndex = i;
                }
                currentBlockSize++;
            } else {
                if (currentBlockSize > maxBlockSize) {
                    maxBlockSize = currentBlockSize;
                    bestStartIndex = startIndex;
                }
                currentBlockSize = 0;
            }
        }
        if (currentBlockSize > maxBlockSize) {
            maxBlockSize = currentBlockSize;
            bestStartIndex = startIndex;
        }
        return new int[]{bestStartIndex, bestStartIndex + maxBlockSize - 1};
    }


    private List<Seat> reserveMiddleSeats(SeatRow seatRow, int ticketsToReserve) {
        int[] freeBlockIndices = findCoordinatesOfLargestFreeBlock(seatRow);
        int start = freeBlockIndices[0];
        int end = freeBlockIndices[1];
        int seatsReserved = 0;
        int mid = (start + end) / 2;

        List<Seat> seats = seatRow.getSeats();
        List<Seat> reservedSeats = new ArrayList<>();

        // Book seats from the middle outwards
        int left = mid, right = mid + 1;
        while (seatsReserved < ticketsToReserve && (left >= start || right <= end)) {
            if (left >= start && seats.get(left).isAvailable()) {
                Seat seatToReserve = seats.get(left);
                seatToReserve.reserveSeat();
                reservedSeats.add(seatToReserve);
                seatsReserved++;
            }
            if (seatsReserved < ticketsToReserve && right <= end && seats.get(right).isAvailable()) {
                Seat seatToReserve = seats.get(right);
                seatToReserve.reserveSeat();
                reservedSeats.add(seatToReserve);
                seatsReserved++;
            }
            left--;
            right++;
        }
        seatRow.decrementAvailableSeatsInRow(seatsReserved);
        totalAvailableSeats -= seatsReserved;
        return reservedSeats;
    }

    public void freeUpReservedSeats(List<Seat> reservedSeats) {
        totalAvailableSeats += reservedSeats.size();
        for (Seat seat : reservedSeats) {
            int seatRowIndex = seat.getSeatRowIndex();
            seat.freeUpSeat();
            seatRowList.get(seatRowIndex).incrementAvailableSeatsInRow();
        }
    }

    public void bookReservedSeats(String bookingId, List<Seat> reservedSeats) {
        for (Seat seat : reservedSeats)
            seat.bookSeat(bookingId);
    }

}
