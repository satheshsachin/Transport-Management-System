package entity;

public class Vehicle {
    private int vehicleId;
    private String vehicleType;
    private int capacity;
    private String status;

    public Vehicle() {}

    public Vehicle(int vehicleId, String vehicleType, int capacity, String status) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.status = status;
    }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Vehicle [vehicleId=" + vehicleId + ", vehicleType=" + vehicleType +
               ", capacity=" + capacity + ", status=" + status + "]";
    }
}
