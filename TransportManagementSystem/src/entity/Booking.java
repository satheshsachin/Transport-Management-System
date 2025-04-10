package entity;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int passengerId;
    private int tripId;
    private int seatsBooked;
    private Date bookingDate;

    public Booking() {}

    public Booking(int bookingId, int passengerId, int tripId, int seatsBooked, Date bookingDate) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.tripId = tripId;
        this.seatsBooked = seatsBooked;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }

    public int getTripId() { return tripId; }
    public void setTripId(int tripId) { this.tripId = tripId; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", passengerId=" + passengerId +
               ", tripId=" + tripId + ", seatsBooked=" + seatsBooked + ", bookingDate=" + bookingDate + "]";
    }
}
