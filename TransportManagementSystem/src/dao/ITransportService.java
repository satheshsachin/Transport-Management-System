package dao;

import java.util.List;
import entity.Vehicle;
import myexceptions.BookingNotFoundException;
import myexceptions.VehicleNotFoundException;
import entity.Route;
import entity.Trip;
import entity.Booking;
import entity.Passenger;

public interface ITransportService {

    // Vehicle Operations
    boolean addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    boolean deleteVehicle(int vehicleId);
    Vehicle getVehicle(int vehicleId) throws VehicleNotFoundException;
    List<Vehicle> getAllVehicles();

    // Route Operations
    boolean addRoute(Route route);
    Route getRoute(int routeId);

    // Trip Operations
    boolean scheduleTrip(Trip trip);
    boolean cancelTrip(int tripId);
    Trip getTrip(int tripId);
    List<Trip> getAllTrips();

    // Passenger Operations
    boolean addPassenger(Passenger passenger);
    Passenger getPassenger(int passengerId);

    // Booking Operations
    boolean bookTicket(Booking booking);
    boolean cancelBooking(int bookingId);
    List<Booking> getBookingsByPassenger(int passengerId) throws BookingNotFoundException;
    List<Booking> getBookingsByTrip(int tripId);
}
