import java.util.*;

// ======================= ABSTRACT USER =======================
abstract class User {
    protected int id;
    protected String name;
    protected String phone;

    public User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    abstract void displayInfo();
}

// ======================= PASSENGER =======================
class Passenger extends User {
    ArrayList<Ride> rideHistory = new ArrayList<>();

    public Passenger(int id, String name, String phone) {
        super(id, name, phone);
    }

    public void addRide(Ride ride) {
        rideHistory.add(ride);
    }

    public void viewRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("No ride history available yet.");
            return;
        }

        System.out.println("Ride History:");
        for (Ride r : rideHistory) {
            r.displayRide();
            System.out.println("----------------");
        }
    }

    @Override
    void displayInfo() {
        System.out.println("Passenger ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }
}

// ======================= DRIVER =======================
class Driver extends User {
    private boolean availabilityStatus;
    private Ride currentRide;

    public Driver(int id, String name, String phone) {
        super(id, name, phone);
        this.availabilityStatus = true;
    }

    public void assignRide(Ride ride) {
        if (!availabilityStatus) {
            System.out.println("Driver not available");
            return;
        }
        currentRide = ride;
        availabilityStatus = false;
        System.out.println("Ride assigned to driver " + name);
    }

    public void completeRide() {
        if (currentRide != null) {
            currentRide.setStatus("Completed");
            currentRide = null;
            availabilityStatus = true;
            System.out.println("Ride completed by " + name);
        } else {
            System.out.println("No ride to complete");
        }
    }

    public boolean isAvailable() {
        return availabilityStatus;
    }

    @Override
    void displayInfo() {
        System.out.println("Driver ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Status: " + (availabilityStatus ? "Available" : "Busy"));
    }
}

// ======================= RIDE =======================
class Ride {
    private int rideId;
    private Passenger passenger;
    private Driver driver;
    private String pickup;
    private String drop;
    private double distance;
    private double fare;
    private String status;

    public Ride(int rideId, Passenger passenger, String pickup, String drop, double distance) {
        this.rideId = rideId;
        this.passenger = passenger;
        this.pickup = pickup;
        this.drop = drop;
        this.distance = distance;
        this.status = "Booked";
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayRide() {
        System.out.println("RideID: " + rideId +
                " | " + pickup + " -> " + drop +
                " | Fare: " + fare +
                " | Status: " + status);
    }
}

// ======================= RIDE SERVICE =======================
class RideService {
    private ArrayList<Ride> rides = new ArrayList<>();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private int rideCounter = 1;

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public Ride createRide(Passenger passenger, String pickup, String drop, double distance) {

        Driver driver = assignDriver();
        if (driver == null) {
            System.out.println("No drivers available!");
            return null;
        }

        Ride ride = new Ride(rideCounter++, passenger, pickup, drop, distance);
        ride.setDriver(driver);

        driver.assignRide(ride);

        double fare = calculateFare(distance);
        ride.setFare(fare);
        ride.setStatus("Ongoing");

        rides.add(ride);
        passenger.addRide(ride);

        System.out.println("Ride booked successfully!");
        System.out.println("Fare: " + fare);

        return ride;
    }

    private Driver assignDriver() {
        for (Driver d : drivers) {
            if (d.isAvailable()) return d;
        }
        return null;
    }

    public double calculateFare(double distance) {
        return 50 + (distance * 20);
    }

    public void completeRide(Driver driver) {
        driver.completeRide();
    }
}

// ======================= MAIN =======================
 class RideSharingApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RideService service = new RideService();
        ArrayList<Passenger> passengers = new ArrayList<>();

        service.addDriver(new Driver(1, "Ali", "123"));
        service.addDriver(new Driver(2, "Ahmed", "456"));

        while (true) {
            System.out.println("\n1. Register Passenger");
            System.out.println("2. Book Ride");
            System.out.println("3. View Ride History");
            System.out.println("4. Complete Ride");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    passengers.add(new Passenger(id, name, phone));
                    System.out.println("Passenger registered!");
                    break;

                case 2:
                    if (passengers.isEmpty()) {
                        System.out.println("No passengers available.");
                        break;
                    }

                    for (Passenger p : passengers) {
                        System.out.println(p.getId() + " - " + p.name);
                    }

                    System.out.print("Select Passenger ID: ");
                    int pid = sc.nextInt();

                    Passenger selected = null;
                    for (Passenger p : passengers) {
                        if (p.getId() == pid) {
                            selected = p;
                            break;
                        }
                    }

                    if (selected == null) {
                        System.out.println("Invalid Passenger!");
                        break;
                    }

                    sc.nextLine();
                    System.out.print("Pickup: ");
                    String pickup = sc.nextLine();

                    System.out.print("Drop: ");
                    String drop = sc.nextLine();

                    System.out.print("Distance: ");
                    double distance = sc.nextDouble();

                    service.createRide(selected, pickup, drop, distance);
                    break;

                case 3:
                    if (!passengers.isEmpty())
                        passengers.get(0).viewRideHistory();
                    break;

                case 4:
                    for (Driver d : service.getDrivers()) {
                        d.displayInfo();
                    }

                    System.out.print("Enter Driver ID: ");
                    int did = sc.nextInt();

                    for (Driver d : service.getDrivers()) {
                        if (d.getId() == did) {
                            service.completeRide(d);
                            break;
                        }
                    }
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }
}