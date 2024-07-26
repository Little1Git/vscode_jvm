package pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runnable_Executor {
    public static void main(String[] args) {
        ArrayList<String> array_List = new ArrayList<>();
        for (int i = 0; i < 20000000; i++) {
            array_List.add("Element " + i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        Runnable task = () -> {
            HashMap<String, Integer> result = Map_from_List(array_List);
            System.out.println(result.size());
            System.out.println("Finish");
        };

        executorService.submit(() -> {
            HashMap<String, Integer> result = Map_from_List(array_List);
            System.out.println(result.size());
            System.out.println("Finish");
        });
        executorService.submit(task);
        executorService.submit(task);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public static HashMap<String, Integer> Map_from_List(ArrayList<String> istock_List) {
        Thread currentThread = Thread.currentThread();
        System.out.println("currentThread: " + currentThread.getName());
        HashMap<String, Integer> iStockSumm = new HashMap<>();
        for (String obj : istock_List) {
            if (!iStockSumm.containsKey(obj)) {
                iStockSumm.put(obj, 0);
            }
            iStockSumm.put(obj, iStockSumm.get(obj) + 1);
        }
        return iStockSumm;
    }

}
