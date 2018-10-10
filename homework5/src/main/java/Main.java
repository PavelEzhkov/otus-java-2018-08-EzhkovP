import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        int size = 50*1000;
        MemoryLeaker memoryLeaker = new MemoryLeaker();
        memoryLeaker.run(size);

    }

}
