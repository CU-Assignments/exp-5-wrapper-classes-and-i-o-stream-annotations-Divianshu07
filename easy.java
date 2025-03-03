//exp 5 easy


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats = 5;
    private final Lock lock = new ReentrantLock();

    public void bookTicket(String passengerName, int priority) {
        Thread.currentThread().setPriority(priority);
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(passengerName + " booked a seat. Remaining seats: " + (--availableSeats));
            } else {
                System.out.println(passengerName + " failed to book a seat. No seats available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class Passenger extends Thread {
    private TicketBookingSystem bookingSystem;
    private String name;

    Passenger(TicketBookingSystem bookingSystem, String name, int priority) {
        this.bookingSystem = bookingSystem;
        this.name = name;
        setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookTicket(name, getPriority());
    }
}

public class hard {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        Passenger p1 = new Passenger(bookingSystem, "VIP 1", Thread.MAX_PRIORITY);
        Passenger p2 = new Passenger(bookingSystem, "VIP 2", Thread.MAX_PRIORITY);
        Passenger p3 = new Passenger(bookingSystem, "Regular 1", Thread.NORM_PRIORITY);
        Passenger p4 = new Passenger(bookingSystem, "Regular 2", Thread.NORM_PRIORITY);
        Passenger p5 = new Passenger(bookingSystem, "Regular 3", Thread.NORM_PRIORITY);
        Passenger p6 = new Passenger(bookingSystem, "Late Passenger", Thread.MIN_PRIORITY);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
    }
}
