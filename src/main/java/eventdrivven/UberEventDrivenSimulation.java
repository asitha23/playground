package eventdrivven;

import java.util.*;
import java.util.concurrent.*;

// --- Observer Pattern Core ---
interface RideEventListener {
    void onRideRequested(RideRequest request);
    void onRideAccepted(RideRequest request, String driverName);
    void onRideCancelled(RideRequest request, String reason);
}

// --- Event Manager ---
class RideEventManager {
    private final List<RideEventListener> listeners = new CopyOnWriteArrayList<>();

    public void subscribe(RideEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(RideEventListener listener) {
        listeners.remove(listener);
    }

    public void notifyRideRequested(RideRequest request) {
        for (RideEventListener listener : listeners) {
            listener.onRideRequested(request);
        }
    }

    public void notifyRideAccepted(RideRequest request, String driverName) {
        for (RideEventListener listener : listeners) {
            listener.onRideAccepted(request, driverName);
        }
    }

    public void notifyRideCancelled(RideRequest request, String reason) {
        for (RideEventListener listener : listeners) {
            listener.onRideCancelled(request, reason);
        }
    }
}

// --- Ride Request ---
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

    public String getPickup() { return pickup; }
    public String getDestination() { return destination; }
}

// --- eventdrivven.Driver (Observer) ---
class Driver implements RideEventListener, Runnable {
    private final String name;
    private final RideEventManager eventManager;
    private RideRequest activeRequest;

    public Driver(String name, RideEventManager manager) {
        this.name = name;
        this.eventManager = manager;
    }

    @Override
    public void onRideRequested(RideRequest request) {
        this.activeRequest = request;
        System.out.println("🚘 " + name + " received new ride request: "
                + request.getPickup() + " ➜ " + request.getDestination());

        // Simulate async behavior
        new Thread(this).start();
    }

    @Override
    public void onRideAccepted(RideRequest request, String driverName) {
        if (!driverName.equals(name) && activeRequest == request) {
            System.out.println("🚫 " + name + ": Ride accepted by " + driverName + ". Cancelling request.");
        }
    }

    @Override
    public void onRideCancelled(RideRequest request, String reason) {
        if (activeRequest == request) {
            System.out.println("❌ " + name + ": Ride cancelled. Reason: " + reason);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 2000)); // Random response delay
            if (activeRequest != null && !activeRequest.isAccepted()) {
                boolean success = activeRequest.accept(name);
                if (success) {
                    System.out.println("✅ " + name + " accepted the ride!");
                    eventManager.notifyRideAccepted(activeRequest, name);
                } else {
                    System.out.println("❌ " + name + " tried to accept but too late.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// --- eventdrivven.Client (Publisher) ---
class Client {
    private final RideEventManager eventManager;

    public Client(RideEventManager manager) {
        this.eventManager = manager;
    }

    public RideRequest requestRide(String pickup, String destination) {
        RideRequest request = new RideRequest(pickup, destination);
        System.out.println("🚕 eventdrivven.Client: Requested ride from " + pickup + " to " + destination);
        eventManager.notifyRideRequested(request);
        return request;
    }
}

// --- Main Simulation ---
public class UberEventDrivenSimulation {
    public static void main(String[] args) throws InterruptedException {
        RideEventManager manager = new RideEventManager();

        // Register drivers
        List<Driver> drivers = Arrays.asList(
                new Driver("Alice", manager),
                new Driver("Bob", manager),
                new Driver("Charlie", manager),
                new Driver("David", manager)
        );

        drivers.forEach(manager::subscribe);

        // eventdrivven.Client requests a ride
        Client client = new Client(manager);
        RideRequest request = client.requestRide("Marina Bay", "Changi Airport");

        // Wait for ride assignment
        Thread.sleep(4000);

        if (request.isAccepted()) {
            System.out.println("\n🎉 Ride assigned to driver: " + request.getAcceptedBy());
            manager.notifyRideCancelled(request, "Ride already taken");
        } else {
            System.out.println("\n⚠️ No driver accepted the ride.");
            manager.notifyRideCancelled(request, "No driver accepted");
        }
    }
}
