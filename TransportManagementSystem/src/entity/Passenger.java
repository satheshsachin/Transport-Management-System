package entity;

public class Passenger {
    private int passengerId;
    private String name;
    private String email;
    private String contactNumber;

    public Passenger() {}

    public Passenger(int passengerId, String name, String email, String contactNumber) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public String toString() {
        return "Passenger [passengerId=" + passengerId + ", name=" + name +
               ", email=" + email + ", contactNumber=" + contactNumber + "]";
    }
}
