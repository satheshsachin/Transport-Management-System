package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.*;
import myexceptions.BookingNotFoundException;
import myexceptions.VehicleNotFoundException;
import util.DBConnUtil;

public class TransportServiceImpl implements ITransportService {

    private static final String DB_FILE = "db.properties";
    private Connection conn = DBConnUtil.getConnection(DB_FILE);

    // ====================== VEHICLE ======================

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicle VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicle.getVehicleId());
            ps.setString(2, vehicle.getVehicleType());
            ps.setInt(3, vehicle.getCapacity());
            ps.setString(4, vehicle.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicle SET vehicle_type=?, capacity=?, status=? WHERE vehicle_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicle.getVehicleType());
            ps.setInt(2, vehicle.getCapacity());
            ps.setString(3, vehicle.getStatus());
            ps.setInt(4, vehicle.getVehicleId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        String sql = "DELETE FROM vehicle WHERE vehicle_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vehicle getVehicle(int vehicleId) throws VehicleNotFoundException {
        String sql = "SELECT * FROM vehicle WHERE vehicle_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getString("vehicle_type"),
                    rs.getInt("capacity"),
                    rs.getString("status")
                );
            } else {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new VehicleNotFoundException("Database error occurred.");
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getString("vehicle_type"),
                    rs.getInt("capacity"),
                    rs.getString("status")
                );
                list.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ====================== ROUTE ======================

    @Override
    public boolean addRoute(Route route) {
        String sql = "INSERT INTO route VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, route.getRouteId());
            ps.setString(2, route.getSource());
            ps.setString(3, route.getDestination());
            ps.setDouble(4, route.getDistanceKm());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Route getRoute(int routeId) {
        String sql = "SELECT * FROM route WHERE route_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, routeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Route(
                    rs.getInt("route_id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getDouble("distance_km")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ====================== TRIP ======================

    @Override
    public boolean scheduleTrip(Trip trip) {
        String sql = "INSERT INTO trip VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trip.getTripId());
            ps.setInt(2, trip.getVehicleId());
            ps.setInt(3, trip.getRouteId());
            ps.setDate(4, trip.getTripDate());
            ps.setString(5, trip.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ====================== BOOKING ======================

    @Override
    public boolean bookTicket(Booking booking) {
        String sql = "INSERT INTO booking VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getBookingId());
            ps.setInt(2, booking.getPassengerId());
            ps.setInt(3, booking.getTripId());
            ps.setInt(4, booking.getSeatsBooked());
            ps.setDate(5, booking.getBookingDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Booking> getBookingsByPassenger(int passengerId) throws BookingNotFoundException {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM booking WHERE passenger_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, passengerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("passenger_id"),
                    rs.getInt("trip_id"),
                    rs.getInt("seats_booked"),
                    rs.getDate("booking_date")
                );
                list.add(booking);
            }

            if (list.isEmpty()) {
                throw new BookingNotFoundException("No bookings found for Passenger ID " + passengerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookingNotFoundException("Database error while fetching bookings.");
        }
        return list;
    }

    // ====================== STUBS (not yet implemented) ======================

    @Override public boolean cancelTrip(int tripId) { return false; }
    @Override public Trip getTrip(int tripId) { return null; }
    @Override public List<Trip> getAllTrips() { return null; }
    @Override public boolean addPassenger(Passenger passenger) { return false; }
    @Override public Passenger getPassenger(int passengerId) { return null; }
    @Override public boolean cancelBooking(int bookingId) { return false; }
    @Override public List<Booking> getBookingsByTrip(int tripId) { return null; }
}
