import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class HeapTest {
    public static void main(String[] args) {
        // 获取 RuntimeMXBean
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("JVM Name: " + runtimeMXBean.getName());
        System.out.println("JVM Version: " + runtimeMXBean.getVmVersion());
        System.out.println("JVM StartTime: " + runtimeMXBean.getStartTime());

        int arraySize = 1024 * 1024; // 1 MB
        for (int i = 0; i < 20; i++) {
            byte[] array = new byte[arraySize];
            System.out.println("array. : " + array.length);
            MemoryShow();
            try {
                Thread.sleep(1000); // Sleep for 1 second to observe the heap expansion and GC
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
}
