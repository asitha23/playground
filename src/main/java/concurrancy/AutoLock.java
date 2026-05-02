package concurrancy;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;

public class AutoLock implements Closeable {

    private static  final  ReentrantLock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
    }

    @Override
    public void close() {
        if (lock.isLocked()) {
            lock.unlock();
            System.out.println("Lock has been released");
        }
    }
}
