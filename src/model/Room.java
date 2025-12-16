package model;

import java.util.Date;

public class Room {
    private int roomNumber;
    private RoomType roomType;
    private int pricePerNight;
    private Date creationDate;

    public Room(int roomNumber, RoomType roomType, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.creationDate = new Date();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [Type: " + roomType + ", Price/Night: " + pricePerNight + "]";
    }
}
