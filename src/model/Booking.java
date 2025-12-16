package model;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Booking {
    private int bookingId;
    private int userId;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;
    private Date bookingDate;
    // Snapshot of room and user data at booking time
    private RoomType roomTypeAtBooking;
    private int roomPriceAtBooking;
    private int userBalanceAtBooking;
    private int totalCost;

    public Booking(int bookingId, int userId, int roomNumber, Date checkIn, Date checkOut,
                   RoomType roomType, int roomPrice, int userBalance, int totalCost) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingDate = new Date();
        this.roomTypeAtBooking = roomType;
        this.roomPriceAtBooking = roomPrice;
        this.userBalanceAtBooking = userBalance;
        this.totalCost = totalCost;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Booking " + bookingId + " [User: " + userId + ", Room: " + roomNumber +
                ", Type: " + roomTypeAtBooking + ", Price/Night: " + roomPriceAtBooking +
                ", CheckIn: " + sdf.format(checkIn) + ", CheckOut: " + sdf.format(checkOut) +
                ", Total Cost: " + totalCost + ", User Balance at Booking: " + userBalanceAtBooking + "]";
    }
}
