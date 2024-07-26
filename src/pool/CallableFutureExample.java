package pool;

import java.util.concurrent.*;

public class CallableFutureExample {
    public static Callable<String> async_task() {
        return () -> {
            Thread currentThread = Thread.currentThread();
            System.out.println("currentThread: " + currentThread.getName());
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Finish");
            return "Task Completed";
        };
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<String> future1 = executor.submit(async_task());
        Future<String> future2 = executor.submit(async_task());
        Future<String> future3 = executor.submit(async_task());

        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("All tasks finished.");
    }
}
