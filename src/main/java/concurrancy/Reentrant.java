package concurrancy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant {
    private static ReentrantLock lock = new ReentrantLock();

    private int counter = 0;

    void main(){
        try(ExecutorService executor = Executors.newFixedThreadPool(5)){
            for (int i = 0; i < 5; i++) {
                executor.submit(increaseCounter());
            }
        }
        System.out.println(counter);
    }

    private Runnable increaseCounter() {
        return () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " Increase counter started");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ", I acquire the lock");
                counter++;
                System.out.println(Thread.currentThread().getName() + " queue " + lock.getQueueLength());
            } finally {
                lock.unlock();
            }
        };
    }
}
