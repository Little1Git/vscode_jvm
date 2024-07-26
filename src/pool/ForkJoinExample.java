package pool;

import java.util.concurrent.*;

public class ForkJoinExample {
    public static class AsyncTask extends RecursiveAction {
        @Override
        protected void compute() {
            Thread currentThread = Thread.currentThread();
            System.out.println("currentThread: " + currentThread.getName());
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        AsyncTask task1 = new AsyncTask();
        AsyncTask task2 = new AsyncTask();
        AsyncTask task3 = new AsyncTask();

        forkJoinPool.execute(task1);
        forkJoinPool.execute(task2);
        forkJoinPool.execute(task3);

        task1.join();
        task2.join();
        task3.join();

        System.out.println("All tasks finished.");
    }
}
