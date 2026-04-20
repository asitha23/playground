package concurrancy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Volatiles {
    static boolean flag = true;

    void main () {
        Runnable runnable = () -> {
            while (flag) {
                try {
                    System.out.println(Thread.currentThread().getName() + "loop running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
            System.out.println(Thread.currentThread().getName() + "loop finished");
        };
        Runnable runnableFlagUpdate = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = false;
            System.out.println(Thread.currentThread().getName() + "flag updated");
        };

        try (ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())){
            executor.submit(runnable);
            executor.submit(runnableFlagUpdate);
        }


    }

}
