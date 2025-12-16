import Service.ServiceImpl;
import Service.ServiceInter;
import model.RoomType;

import java.text.SimpleDateFormat;


public class HotelReservationSystem {
    public static void main(String[] args) {
        ServiceInter service = new ServiceImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Create 3 rooms
            service.setRoom(1, RoomType.STANDARD, 1000);
            service.setRoom(2, RoomType.JUNIOR, 2000);
            service.setRoom(3, RoomType.SUITE, 3000);

            // Create 2 users
            service.setUser(1, 5000);
            service.setUser(2, 10000);

            System.out.println("\n--- Starting Bookings ---\n");

            // User 1 tries booking Room 2 from 30/06/2026 to 07/07/2026 (7 nights)
            System.out.println("1. User 1 books Room 2 (30/06/2026 - 07/07/2026):");
            service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"));

            // User 1 tries booking Room 2 from 07/07/2026 to 30/06/2026 (invalid dates)
            System.out.println("\n2. User 1 books Room 2 (07/07/2026 - 30/06/2026):");
            service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"));

            // User 1 tries booking Room 1 from 07/07/2026 to 08/07/2026 (1 night)
            System.out.println("\n3. User 1 books Room 1 (07/07/2026 - 08/07/2026):");
            service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

            // User 2 tries booking Room 1 from 07/07/2026 to 09/07/2026 (2 nights)
            System.out.println("\n4. User 2 books Room 1 (07/07/2026 - 09/07/2026):");
            service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026"));

            // User 2 tries booking Room 3 from 07/07/2026 to 08/07/2026 (1 night)
            System.out.println("\n5. User 2 books Room 3 (07/07/2026 - 08/07/2026):");
            service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

            // Update Room 1
            System.out.println("\n6. Updating Room 1:");
            service.setRoom(1, RoomType.SUITE, 10000);

            // Print all data
            service.printAll();
            service.printAllUsers();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}