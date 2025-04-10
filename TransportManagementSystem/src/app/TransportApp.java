package app;

import java.util.List;
import java.util.Scanner;

import dao.ITransportService;
import dao.TransportServiceImpl;
import entity.*;

import myexceptions.BookingNotFoundException;
import myexceptions.VehicleNotFoundException;

public class TransportApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ITransportService service = new TransportServiceImpl();

        System.out.println("Connecting to DB...");
        java.sql.Connection conn = util.DBConnUtil.getConnection("db.properties");
        System.out.println(conn != null ? "✅ Connected to DB!" : "❌ Failed to connect to DB.");

        int choice = 0;

        do {
            System.out.println("\n===== TRANSPORT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Add Route");
            System.out.println("4. Schedule Trip");
            System.out.println("5. View All Trips");
            System.out.println("6. Add Passenger");
            System.out.println("7. Book Ticket");
            System.out.println("8. Cancel Booking");
            System.out.println("9. View Bookings By Passenger ID");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle ID: ");
                    int vId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Vehicle Type: ");
                    String vType = sc.nextLine();
                    System.out.print("Enter Capacity: ");
                    int capacity = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Status: ");
                    String vStatus = sc.nextLine();

                    Vehicle v = new Vehicle(vId, vType, capacity, vStatus);
                    boolean added = service.addVehicle(v);
                    System.out.println(added ? "Vehicle added successfully!" : "Failed to add vehicle.");
                    break;

                case 2:
                    List<Vehicle> vehicles = service.getAllVehicles();
                    vehicles.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Route ID: ");
                    int rId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Source: ");
                    String source = sc.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();
                    System.out.print("Enter Distance (km): ");
                    double dist = sc.nextDouble();

                    Route route = new Route(rId, source, destination, dist);
                    boolean routeAdded = service.addRoute(route);
                    System.out.println(routeAdded ? "Route added successfully!" : "Failed to add route.");
                    break;

                case 4:
                    System.out.print("Enter Trip ID: ");
                    int tId = sc.nextInt();
                    System.out.print("Enter Vehicle ID: ");
                    int tVehId = sc.nextInt();
                    System.out.print("Enter Route ID: ");
                    int tRouteId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Trip Date (yyyy-mm-dd): ");
                    java.sql.Date tripDate = java.sql.Date.valueOf(sc.nextLine());
                    System.out.print("Enter Status: ");
                    String tStatus = sc.nextLine();

                    Trip trip = new Trip(tId, tVehId, tRouteId, tripDate, tStatus);
                    boolean tripScheduled = service.scheduleTrip(trip);
                    System.out.println(tripScheduled ? "Trip scheduled!" : "Failed to schedule trip.");
                    break;

                case 5:
                    List<Trip> trips = service.getAllTrips();
                    trips.forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Enter Passenger ID: ");
                    int pId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String pName = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String pEmail = sc.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String pContact = sc.nextLine();

                    Passenger p = new Passenger(pId, pName, pEmail, pContact);
                    boolean pAdded = service.addPassenger(p);
                    System.out.println(pAdded ? "Passenger added!" : "Failed to add passenger.");
                    break;

                case 7:
                    System.out.print("Enter Booking ID: ");
                    int bId = sc.nextInt();
                    System.out.print("Enter Passenger ID: ");
                    int bPassId = sc.nextInt();
                    System.out.print("Enter Trip ID: ");
                    int bTripId = sc.nextInt();
                    System.out.print("Enter Seats Booked: ");
                    int seats = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Booking Date (yyyy-mm-dd): ");
                    java.sql.Date bDate = java.sql.Date.valueOf(sc.nextLine());

                    Booking booking = new Booking(bId, bPassId, bTripId, seats, bDate);
                    boolean booked = service.bookTicket(booking);
                    System.out.println(booked ? "Ticket booked!" : "Booking failed.");
                    break;

                case 8:
                    System.out.print("Enter Booking ID to Cancel: ");
                    int delId = sc.nextInt();
                    boolean deleted = service.cancelBooking(delId);
                    System.out.println(deleted ? "Booking cancelled." : "Booking not found.");
                    break;

                case 9:
                    System.out.print("Enter Passenger ID: ");
                    int searchId = sc.nextInt();
                    try {
                        List<Booking> bookings = service.getBookingsByPassenger(searchId);
                        bookings.forEach(System.out::println);
                    } catch (BookingNotFoundException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;

                case 10:
                    System.out.println("Exiting system... 🚪");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 10);

        sc.close();
    }
}