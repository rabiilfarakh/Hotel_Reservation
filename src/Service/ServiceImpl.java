package Service;

import model.Booking;
import model.Room;
import model.RoomType;
import model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ServiceImpl implements ServiceInter{
    private ArrayList<Room> rooms;
    private ArrayList<User> users;
    private ArrayList<Booking> bookings;
    private int bookingIdCounter;

    public ServiceImpl() {
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.bookingIdCounter = 1;
    }

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        try {
            if (roomNumber <= 0) {
                throw new IllegalArgumentException("Room number must be positive");
            }
            if (roomPricePerNight < 0) {
                throw new IllegalArgumentException("Price per night cannot be negative");
            }

            Room existingRoom = findRoomByNumber(roomNumber);
            if (existingRoom != null) {
                existingRoom.setRoomType(roomType);
                existingRoom.setPricePerNight(roomPricePerNight);
                System.out.println("Room " + roomNumber + " updated successfully.");
            } else {
                Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
                rooms.add(newRoom);
                System.out.println("Room " + roomNumber + " created successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error setting room: " + e.getMessage());
        }
    }

    public void setUser(int userId, int balance) {
        try {
            if (userId <= 0) {
                throw new IllegalArgumentException("User ID must be positive");
            }
            if (balance < 0) {
                throw new IllegalArgumentException("Balance cannot be negative");
            }

            User existingUser = findUserById(userId);
            if (existingUser != null) {
                existingUser.setBalance(balance);
                System.out.println("User " + userId + " updated successfully.");
            } else {
                User newUser = new User(userId, balance);
                users.add(newUser);
                System.out.println("User " + userId + " created successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error setting user: " + e.getMessage());
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        try {
            if (checkIn == null || checkOut == null) {
                throw new IllegalArgumentException("Check-in and check-out dates cannot be null");
            }

            Calendar calIn = Calendar.getInstance();
            calIn.setTime(checkIn);
            calIn.set(Calendar.HOUR_OF_DAY, 0);
            calIn.set(Calendar.MINUTE, 0);
            calIn.set(Calendar.SECOND, 0);
            calIn.set(Calendar.MILLISECOND, 0);
            checkIn = calIn.getTime();

            Calendar calOut = Calendar.getInstance();
            calOut.setTime(checkOut);
            calOut.set(Calendar.HOUR_OF_DAY, 0);
            calOut.set(Calendar.MINUTE, 0);
            calOut.set(Calendar.SECOND, 0);
            calOut.set(Calendar.MILLISECOND, 0);
            checkOut = calOut.getTime();

            if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
                throw new IllegalArgumentException("Check-out date must be after check-in date");
            }

            User user = findUserById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }

            Room room = findRoomByNumber(roomNumber);
            if (room == null) {
                throw new IllegalArgumentException("Room not found");
            }

            long diffInMillis = checkOut.getTime() - checkIn.getTime();
            int nights = (int) (diffInMillis / (1000 * 60 * 60 * 24));
            int totalCost = nights * room.getPricePerNight();

            if (user.getBalance() < totalCost) {
                throw new IllegalArgumentException("Insufficient balance. Required: " + totalCost + ", Available: " + user.getBalance());
            }

            if (!isRoomAvailable(roomNumber, checkIn, checkOut)) {
                throw new IllegalArgumentException("Room is not available for the specified period");
            }

            Booking booking = new Booking(bookingIdCounter++, userId, roomNumber, checkIn, checkOut,
                    room.getRoomType(), room.getPricePerNight(), user.getBalance(), totalCost);
            bookings.add(booking);

            user.setBalance(user.getBalance() - totalCost);

            System.out.println("Booking successful! Total cost: " + totalCost + ". Remaining balance: " + user.getBalance());
        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    public void printAll() {
        System.out.println("\n========== ALL ROOMS (Latest to Oldest) ==========");
        ArrayList<Room> sortedRooms = new ArrayList<>(rooms);
        sortedRooms.sort((r1, r2) -> r2.getCreationDate().compareTo(r1.getCreationDate()));
        for (Room room : sortedRooms) {
            System.out.println(room);
        }

        System.out.println("\n========== ALL BOOKINGS (Latest to Oldest) ==========");
        ArrayList<Booking> sortedBookings = new ArrayList<>(bookings);
        sortedBookings.sort((b1, b2) -> b2.getBookingDate().compareTo(b1.getBookingDate()));
        for (Booking booking : sortedBookings) {
            System.out.println(booking);
        }
        System.out.println();
    }

    public void printAllUsers() {
        System.out.println("\n========== ALL USERS (Latest to Oldest) ==========");
        ArrayList<User> sortedUsers = new ArrayList<>(users);
        sortedUsers.sort((u1, u2) -> u2.getCreationDate().compareTo(u1.getCreationDate()));
        for (User user : sortedUsers) {
            System.out.println(user);
        }
        System.out.println();
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == roomNumber) {
                if (!(checkOut.before(booking.getCheckIn()) || checkOut.equals(booking.getCheckIn()) ||
                        checkIn.after(booking.getCheckOut()) || checkIn.equals(booking.getCheckOut()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
