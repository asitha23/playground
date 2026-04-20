package concurrancy;

public class GracefullyStopAThread {
    void main() throws InterruptedException {
        Runnable r = () -> {
            long start = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " Starting GracefullyStopAThread " + start);
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "loop running");
            }
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "Stopping GracefullyStopAThread " + end);
            System.out.println(Thread.currentThread().getName() + "Time taken: " + (end - start));
        };
        Thread worker = new Thread(r);
        Thread worker2 = new Thread(r);

        worker.start();
        worker2.start();
        Thread.sleep(50);
        worker.interrupt();
        worker2.interrupt();
    }
}
