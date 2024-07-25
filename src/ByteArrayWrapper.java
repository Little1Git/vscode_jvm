import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayWrapper {
    private List<byte[]> byteArrays;

    public ByteArrayWrapper(int numberOfArrays, int arraySize) {
        byteArrays = new ArrayList<>(numberOfArrays);
        for (int i = 0; i < numberOfArrays; i++) {
            byteArrays.add(new byte[arraySize]);
        }
    }

    // private byte[][] byteArrays;

    // public ByteArrayWrapper(int numberOfArrays, int arraySize) {
    // // 创建一个二维数组，第一维的大小是 numberOfArrays
    // byteArrays = new byte[numberOfArrays][arraySize];
    // // for (int i = 0; i < numberOfArrays; i++) {
    // // byteArrays[i] = new byte[arraySize];
    // // }
    // }

    public static void main(String[] args) {

        young_old();

        // 创建一个 ByteArrayWrapper 实例，包含 500 个 1MB 的字节数组
        // ByteArrayWrapper wrapper = new ByteArrayWrapper(1024, 1024 * 1023);
        // young_old();

        // System.out.println(wrapper.getClass());

        // byte[][] byteArrays = new byte[1024][1024 * 1024];
        // byte[] byteArrays = new byte[1024 * 1024 * 1824];
        byte[][] byteArrays = new byte[1024][1024 * 1024];
        // byte[][] byteArrays = new byte[1024 * 1024][1025];

        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        System.out.println();
        System.out.println("已分配内存: " + totalMemory / (1024 * 1024) + " MB");

        young_old();

        System.out.println();

    }

    public static void young_old() {
        // 获取 MemoryMXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 获取堆内存的使用情况
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        // 获取新生代和老年代的内存使用情况
        MemoryUsage youngGen = ManagementFactory.getMemoryPoolMXBeans().stream()
                .filter(pool -> (pool.getName().contains("Eden") || pool.getName().contains("Surviv")))
                // .filter(pool -> (pool.getName().contains("Eden") ))
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
        System.out.println("已提交大小: " + youngGen.getCommitted() / (1024 * 1024) +
        "MB");
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
