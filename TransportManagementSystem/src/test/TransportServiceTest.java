package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.*;

import dao.ITransportService;
import dao.TransportServiceImpl;
import entity.*;

public class TransportServiceTest {

    ITransportService service;

    @BeforeEach
    void setUp() {
        service = new TransportServiceImpl();

        try (Connection conn = util.DBConnUtil.getConnection("db.properties")) {

            // Insert test Passenger (401)
            PreparedStatement insertPassenger = conn.prepareStatement(
                "INSERT IGNORE INTO passenger (passenger_id, name, email, contact_number) VALUES (401, 'Test Passenger', 'test@example.com', '9999999999')");
            insertPassenger.executeUpdate();

            // Insert test Vehicle (999)
            PreparedStatement insertVehicle = conn.prepareStatement(
                "INSERT IGNORE INTO vehicle (vehicle_id, vehicle_type, capacity, status) VALUES (999, 'TestVan', 20, 'Available')");
            insertVehicle.executeUpdate();

            // Insert test Route (999)
            PreparedStatement insertRoute = conn.prepareStatement(
                "INSERT IGNORE INTO route (route_id, source, destination, distance_km) VALUES (999, 'Delhi', 'Agra', 200)");
            insertRoute.executeUpdate();

            // Insert test Trip (301)
            PreparedStatement insertTrip = conn.prepareStatement(
                "INSERT IGNORE INTO trip (trip_id, vehicle_id, route_id, trip_date, status) VALUES (301, 999, 999, '2025-05-20', 'Scheduled')");
            insertTrip.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddVehicle() {
        Vehicle v = new Vehicle(888, "TestBus", 30, "Available");
        boolean result = service.addVehicle(v);
        assertTrue(result, "Vehicle should be added successfully");
    }

    @Test
    void testScheduleTrip() {
        Trip trip = new Trip(888, 999, 999, Date.valueOf("2025-05-20"), "Scheduled");
        boolean result = service.scheduleTrip(trip);
        assertTrue(result, "Trip should be scheduled successfully");
    }

    @Test
    void testBookTicket() {
        Booking booking = new Booking(888, 401, 301, 2, Date.valueOf("2025-04-10"));
        boolean result = service.bookTicket(booking);
        assertTrue(result, "Ticket should be booked successfully");
    }

    @AfterEach
    void tearDown() {
        try (Connection conn = util.DBConnUtil.getConnection("db.properties")) {

            // Cleanup test data
            conn.prepareStatement("DELETE FROM vehicle WHERE vehicle_id = 888").executeUpdate();
            conn.prepareStatement("DELETE FROM trip WHERE trip_id = 888").executeUpdate();
            conn.prepareStatement("DELETE FROM booking WHERE booking_id = 888").executeUpdate();

            // Optional: cleanup setup data too
            conn.prepareStatement("DELETE FROM booking WHERE booking_id = 888").executeUpdate();
            conn.prepareStatement("DELETE FROM trip WHERE trip_id = 301").executeUpdate();
            conn.prepareStatement("DELETE FROM vehicle WHERE vehicle_id = 999").executeUpdate();
            conn.prepareStatement("DELETE FROM route WHERE route_id = 999").executeUpdate();
            conn.prepareStatement("DELETE FROM passenger WHERE passenger_id = 401").executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
