package pool;

import java.util.concurrent.TimeUnit;

public class ThreadExample {
    public static void async_task() {
        Thread currentThread = Thread.currentThread();
        System.out.println("currentThread: " + currentThread.getName());
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> async_task());
        Thread thread2 = new Thread(() -> async_task());
        Thread thread3 = new Thread(() -> async_task());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks finished.");
    }
}
