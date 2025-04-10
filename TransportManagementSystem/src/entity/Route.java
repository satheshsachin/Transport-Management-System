package entity;

public class Route {
    private int routeId;
    private String source;
    private String destination;
    private double distanceKm;

    public Route() {}

    public Route(int routeId, String source, String destination, double distanceKm) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.distanceKm = distanceKm;
    }

    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    @Override
    public String toString() {
        return "Route [routeId=" + routeId + ", source=" + source +
               ", destination=" + destination + ", distanceKm=" + distanceKm + "]";
    }
}
