package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {
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
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 使用lambda表达式传递async_task
        executor.submit(() -> async_task());
        executor.submit(() -> async_task());
        executor.submit(() -> async_task());

        // 关闭线程池
        executor.shutdown();
        try {
            // 等待所有任务完成
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("All tasks finished.");
    }
}
