import java.util.*;
import java.util.concurrent.*;

class RideRequest {
    private final String pickup;
    private final String destination;
    private volatile boolean accepted = false;
    private volatile String acceptedBy = null;

    public RideRequest(String pickup, String destination) {
        this.pickup = pickup;
        this.destination = destination;
    }

    public synchronized boolean accept(String driverName) {
        if (!accepted) {
            accepted = true;
            acceptedBy = driverName;
            System.out.println("✅ " + driverName + " accepted the ride from " + pickup + " to " + destination);
            return true;
        }
        return false;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }
}

class Driver implements Runnable {
    private final String name;
    private final RideRequest request;

    public Driver(String name, RideRequest request) {
        this.name = name;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            // Simulate random response delay
            Thread.sleep((long) (Math.random() * 3000));

            if (request.isAccepted()) {
                System.out.println("🚫 " + name + ": Request already accepted by " + request.getAcceptedBy());
                return;
            }

            boolean accepted = request.accept(name);
            if (!accepted) {
                System.out.println("❌ " + name + " tried to accept but too late.");
            } else {
                // Notify others indirectly — simulation
                System.out.println("📢 " + name + ": Ride confirmed! Other drivers will be notified to cancel.");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Client {
    public RideRequest requestRide(String pickup, String destination) {
        System.out.println("🚕 Client requested a ride from " + pickup + " to " + destination);
        return new RideRequest(pickup, destination);
    }
}

public class UberRideSimulation {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        RideRequest rideRequest = client.requestRide("Marina Bay", "Changi Airport");

        List<String> driverNames = Arrays.asList("Alice", "Bob", "Charlie", "David");
        ExecutorService executor = Executors.newFixedThreadPool(driverNames.size());

        for (String name : driverNames) {
            executor.submit(new Driver(name, rideRequest));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        if (rideRequest.isAccepted()) {
            System.out.println("\n🎉 Ride successfully assigned to driver: " + rideRequest.getAcceptedBy());
        } else {
            System.out.println("\n⚠️ No driver accepted the ride in time.");
        }
    }
}
