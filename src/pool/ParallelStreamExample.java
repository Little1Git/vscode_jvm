package pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParallelStreamExample {
    public static void async_task(Integer item) {
        Thread currentThread = Thread.currentThread();
        System.out.println("currentThread: " + currentThread.getName() + ", item: " + item);
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Finish: " + item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        list.parallelStream().forEach(item -> async_task(item));

        System.out.println("All tasks finished.");
    }
}
