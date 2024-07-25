import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class Heapsize {
    public static void main(String[] args) {
        byte[] byteArrays = new byte[1000_000_000];
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        long heapInit = heapMemoryUsage.getInit(); // 初始堆大小
        long heapUsed = heapMemoryUsage.getUsed(); // 已使用的堆大小
        long heapCommitted = heapMemoryUsage.getCommitted(); // 堆的当前分配大小
        long heapMax = heapMemoryUsage.getMax(); // 最大堆大小

        System.out.println("初始堆大小: " + heapInit / 1024 / 1024 + " MB");
        System.out.println("已使用的堆大小: " + heapUsed / 1024 / 1024 + " MB");
        System.out.println("堆的当前分配大小: " + heapCommitted / 1024 / 1024 + " MB");
        System.out.println("最大堆大小: " + heapMax / 1024 / 1024 + " MB");
    }
}
