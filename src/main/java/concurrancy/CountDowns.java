package concurrancy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDowns {

    private static final CountDownLatch latch = new CountDownLatch(2);

    static void main(String[] arg) throws InterruptedException {

        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
            executor.submit(() -> task(1000));
            executor.submit(() -> task(2000));
            executor.submit(() -> task(3000));
            executor.submit(() -> task(7000));
        }
        latch.await();
        System.out.println("main finished");

        /**
         * pool-1-thread-1: waiting for 1000 seconds
         * pool-1-thread-2: waiting for 2000 seconds
         * pool-1-thread-3: waiting for 3000 seconds
         * pool-1-thread-4: waiting for 7000 seconds
         * pool-1-thread-1: done
         * pool-1-thread-2: done
         * pool-1-thread-3: done
         * pool-1-thread-4: done
         * main finished
         *
         * The output will be look like above why,
         * :: resource try is internally calling ExecutorService await Termination which is blocking
         * latch got nothing to do with this as it is already triggered with two.
         */
    }

    private static void task(long wait) {
        try {
            System.out.println(Thread.currentThread().getName() + ": waiting for " + wait + " seconds");
            Thread.sleep(wait);
            System.out.println(Thread.currentThread().getName() + ": done");
        } catch (InterruptedException ignored){

        }
        latch.countDown();
    }
}
