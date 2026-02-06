package collectionsframework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.IO.println;

public class ArrayListSynchronized {

    void main() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        //list = ;
        Runnable r = () -> {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                Integer intVal = random.nextInt(1000);
                println(intVal + " " + Thread.currentThread().getName());
                list.add(intVal);
            }
            list.remove(random.nextInt(100));
            println("Size of the list: " + list.size() + " " + Thread.currentThread().getName());
        };

        //ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(r);
//            t.setDaemon(true);
            t.start();
            //Future<?> future = executor.submit(t);
        }
        //executor.awaitTermination(1, TimeUnit.MINUTES);
        //Thread.sleep(1000);
    }
}
