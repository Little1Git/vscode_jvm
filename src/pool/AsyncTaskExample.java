package pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AsyncTaskExample {
    // 定义静态方法 async_task()
    public static void async_task() {
        Thread currentThread = Thread.currentThread();
        System.out.println("currentThread: " + currentThread.getName());
        try {
            // 等待3秒钟
            TimeUnit.SECONDS.sleep(3);
            // 输出 "Finish"
            System.out.println("Finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {   //JDK 8
        // 创建多个异步任务
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> async_task());
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> async_task());
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> async_task());

        // 等待所有异步任务完成
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

        try {
            // 等待所有任务完成
            allTasks.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks finished.");
    }
}
