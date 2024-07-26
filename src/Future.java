import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Future {
    public String async_task() {
        Thread currentThread = Thread.currentThread();
        System.out.println("currentThread: " + currentThread.getName());
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Finish 3s");
            return "Finish Return";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static void main(String[] args) {
        Future myClassInstance = new Future(); // 创建类的实例

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> myClassInstance.async_task());
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> myClassInstance.async_task());
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> myClassInstance.async_task());

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

        System.out.println("11111111111111111");

        // 阻塞调用它的程序，直到所有异步任务完成
        allTasks.join();

        System.out.println("22222222222222222");

        try {
            System.out.println("Task1 result: " + task1.get());
            System.out.println("Task2 result: " + task2.get());
            System.out.println("Task3 result: " + task3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
