package entity;

import java.sql.Date;

public class Trip {
    private int tripId;
    private int vehicleId;
    private int routeId;
    private Date tripDate;
    private String status;

    public Trip() {}

    public Trip(int tripId, int vehicleId, int routeId, Date tripDate, String status) {
        this.tripId = tripId;
        this.vehicleId = vehicleId;
        this.routeId = routeId;
        this.tripDate = tripDate;
        this.status = status;
    }

    public int getTripId() { return tripId; }
    public void setTripId(int tripId) { this.tripId = tripId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }

    public Date getTripDate() { return tripDate; }
    public void setTripDate(Date tripDate) { this.tripDate = tripDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Trip [tripId=" + tripId + ", vehicleId=" + vehicleId +
               ", routeId=" + routeId + ", tripDate=" + tripDate + ", status=" + status + "]";
    }
}
