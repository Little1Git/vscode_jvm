import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.MemoryPoolMXBean;

public class MemoryUsageExample {
    public static void main(String[] args) {
        young_old();
    }

    public static void young_old() {
                // 获取 MemoryMXBean
                MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        
                // 获取堆内存的使用情况
                MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
                // 获取新生代和老年代的内存使用情况
                MemoryUsage youngGen = ManagementFactory.getMemoryPoolMXBeans().stream()
                        .filter(pool -> (pool.getName().contains("Eden")||pool.getName().contains("Survivor")))
                        .findFirst()
                        .map(MemoryPoolMXBean::getUsage)
                        .orElse(null);
                MemoryUsage oldGen = ManagementFactory.getMemoryPoolMXBeans().stream()
                        .filter(pool -> pool.getName().contains("Old"))
                        .findFirst()
                        .map(MemoryPoolMXBean::getUsage)
                        .orElse(null);
                
                // 打印内存使用情况
                System.out.println("堆内存使用情况:");
                System.out.println("初始大小: " + heapMemoryUsage.getInit() / (1024 * 1024) + "MB");
                System.out.println("已使用大小: " + heapMemoryUsage.getUsed() / (1024 * 1024) + "MB");
                System.out.println("已提交大小: " + heapMemoryUsage.getCommitted() / (1024 * 1024) + "MB");
                System.out.println("最大大小: " + heapMemoryUsage.getMax() / (1024 * 1024) + "MB");
        
                System.out.println("\n新生代内存使用情况:");
                if (youngGen != null) {
                    System.out.println("初始大小: " + youngGen.getInit() / (1024 * 1024) + "MB");
                    System.out.println("已使用大小: " + youngGen.getUsed() / (1024 * 1024) + "MB");
                    System.out.println("已提交大小: " + youngGen.getCommitted() / (1024 * 1024) + "MB");
                    System.out.println("最大大小: " + youngGen.getMax() / (1024 * 1024) + "MB");
                } else {
                    System.out.println("无法获取新生代内存使用情况");
                }
        
                System.out.println("\n老年代内存使用情况:");
                if (oldGen != null) {
                    System.out.println("初始大小: " + oldGen.getInit() / (1024 * 1024) + "MB");
                    System.out.println("已使用大小: " + oldGen.getUsed() / (1024 * 1024) + "MB");
                    System.out.println("已提交大小: " + oldGen.getCommitted() / (1024 * 1024) + "MB");
                    System.out.println("最大大小: " + oldGen.getMax() / (1024 * 1024) + "MB");
                } else {
                    System.out.println("无法获取老年代内存使用情况");
                }
    }
}
