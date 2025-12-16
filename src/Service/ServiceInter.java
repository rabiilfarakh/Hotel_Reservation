package Service;

import model.Room;
import model.RoomType;
import model.User;

import java.util.ArrayList;
import java.util.Date;
public interface ServiceInter {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight);

    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut);

    void printAll();

    void setUser(int userId, int balance);

    void printAllUsers();
}
