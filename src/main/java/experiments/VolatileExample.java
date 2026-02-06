package experiments;

public class VolatileExample {
    // Try toggling the 'volatile' keyword here to see the difference
    private static boolean sayHello = false;

    public static void main(String[] args) throws InterruptedException {
        
        // Thread 1: The Reader
        Thread readerThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "Reader started, waiting for flag...");
            while (!sayHello) {
                // Without 'volatile', the thread may cache sayHello = false 
                // and stay in this loop forever, even after the value changes.
            }
            System.out.println(Thread.currentThread().getName() + "Reader detected change! Hello!");
        });

        readerThread.start();

        // Pause main thread for a second to ensure Reader is looping
        Thread.sleep(1000);

        // Thread 2: The Writer
        Thread writerThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +  "Writer is changing the flag...");
            sayHello = true;
            System.out.println(Thread.currentThread().getName() +  "Writer finished.");
        });

        writerThread.start();
    }
}