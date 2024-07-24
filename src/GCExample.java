import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
// import java.util.LinkedList;
import java.util.List;


class Data {
    byte[] data;
    Data(byte[] data){
        this.data=data;
    }
}

public class GCExample {
    public static void main(String[] args) {
        // 创建大量对象以促使垃圾回收
        List<Object> list = new ArrayList<>();
        // List<Object> list = new LinkedList<>();
        Data data1 = new Data(new byte[1024 * 1024 * 500]);
        

        MemoryShow();
        young_old();
        // 打印 JVM 的初始垃圾回收信息
        printGarbageCollectorInfo("初始状态");

        // 创建大量对象
        for (int i = 0; i < 500; i++) {
            list.add(new byte[1024 * 1024]); // 分配 1MB 的字节数组
        }

        // 手动触发垃圾回收
        System.gc();

        MemoryShow();
        young_old();
        // 打印垃圾回收后的信息
        printGarbageCollectorInfo("垃圾回收后状态");

        // // 再创建更多对象
        // for (int i = 0; i < 500; i++) {
        //     list.add(new byte[1024 * 1024]); // 分配 1MB 的字节数组
        // }

        // // 手动触发垃圾回收
        // System.gc();

        // MemoryShow();
        // young_old();
        // // 打印垃圾回收后的信息
        // printGarbageCollectorInfo("再次垃圾回收后状态");

        // // ---------------------------------------------------------------
        // // 再创建更多对象
        // for (int i = 0; i < 500; i++) {
        //     list.add(new byte[1024 * 1024]); // 分配 1MB 的字节数组
        // }

        // // 手动触发垃圾回收
        // System.gc();

        // MemoryShow();
        // young_old();
        // // 打印垃圾回收后的信息
        // printGarbageCollectorInfo("再次垃圾回收后状态");

        System.out.println(data1.getClass());
    }

    private static void printGarbageCollectorInfo(String label) {
        System.out.println(label);
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("垃圾回收器: " + gcBean.getName());
            System.out.println("垃圾回收次数: " + gcBean.getCollectionCount());
            System.out.println("垃圾回收时间: " + gcBean.getCollectionTime() + " ms");
        }
        System.out.println();
    }

    public static void MemoryShow() {
        // 获取 MemoryMXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        System.out.println("HeapUsage: " + heapMemoryUsage);
        System.out.println("nonHeapUsage: " + nonHeapMemoryUsage);

        // 获取 JVM 各个垃圾回收器的详细信息
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        System.out.println(gcBeans);
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("gcBean.getName: " + gcBean.getName());
            System.out.println("gcBean.getCollectionCount: " + gcBean.getCollectionCount());
            System.out.println("gcBean.getCollectionTime: " + gcBean.getCollectionTime() + " ms");
        }
    }

    public static void young_old() {
        // 获取 MemoryMXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 获取堆内存的使用情况
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        // 获取新生代和老年代的内存使用情况
        MemoryUsage youngGen = ManagementFactory.getMemoryPoolMXBeans().stream()
                .filter(pool -> (pool.getName().contains("Eden") || pool.getName().contains("Survivor")))
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
