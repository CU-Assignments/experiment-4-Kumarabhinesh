//evelop a ticket booking system with synchronized threads to ensure no double booking of seats. Use thread priorities to simulate VIP bookings being processed first.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TicketBookingSystem {
    private int availableSeats = 10; // Total available seats

    public synchronized boolean bookSeat(String customerType) {
        if (availableSeats > 0) {
            System.out.println(customerType + " booking a seat. Seats left: " + (availableSeats - 1));
            availableSeats--;
            return true;
        } else {
            System.out.println(customerType + " tried to book a seat, but no seats are available.");
            return false;
        }
    }
}

class BookingTask implements Runnable {
    private final TicketBookingSystem bookingSystem;
    private final String customerType;

    public BookingTask(TicketBookingSystem bookingSystem, String customerType) {
        this.bookingSystem = bookingSystem;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(customerType);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            Thread vipThread = new Thread(new BookingTask(bookingSystem, "VIP"));
            vipThread.setPriority(Thread.MAX_PRIORITY);
            executor.execute(vipThread);
        }

        for (int i = 0; i < 5; i++) {
            Thread regularThread = new Thread(new BookingTask(bookingSystem, "Regular"));
            regularThread.setPriority(Thread.MIN_PRIORITY);
            executor.execute(regularThread);
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
