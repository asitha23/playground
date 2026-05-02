package concurrancy;

import java.util.concurrent.StructuredTaskScope;

public class ScopedTask {

    void main () {
        // StructuredTaskScope is preview on Java 25
        try(var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.anySuccessfulResultOrThrow())) {
            scope.fork(this::task1);
            scope.fork(this::task2);

            var res = scope.join();
            System.out.println(res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    String task1() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(" task 1 interrupted");
            Thread.currentThread().interrupt();
        }
        return "I am a task 1 completed";
    }

    String task2() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(" task 2 interrupted");
            Thread.currentThread().interrupt();
        }
        return "I am a task 2 completed";
    }
}
