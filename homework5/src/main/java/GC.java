import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GC {
    static final Set<String> YOUNG_GC = new HashSet<String>(4);
    static final Set<String> OLD_GC = new HashSet<String>(4);

    static {
        // young generation GC names
        YOUNG_GC.add("Copy");
        YOUNG_GC.add("PS Scavenge");
        YOUNG_GC.add("ParNew");
        YOUNG_GC.add("G1 Young Generation");

        // old generation GC names
        OLD_GC.add("MarkSweepCompact");
        OLD_GC.add("PS MarkSweep");
        OLD_GC.add("ConcurrentMarkSweep");
        OLD_GC.add("G1 Old Generation");
    }
    public static void printGCstatic(){
        long minorCount = 0;
        long minorTime = 0;
        long majorCount = 0;
        long majorTime = 0;

        List<GarbageCollectorMXBean> mxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : mxBeans){
            long count = gc.getCollectionCount();
            if (count >= 0){
                if (YOUNG_GC.contains(gc.getName())){
                    minorCount += count;
                    majorTime += gc.getCollectionTime();
                } else if (OLD_GC.contains(gc.getName())){
                    majorCount += count;
                    majorTime += gc.getCollectionTime();
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MinorGC -> Count: ").append(minorCount)
                .append(", Time (ms): ").append(minorTime)
                .append(", MajorGC -> Count: ").append(majorCount)
                .append(", Time (ms): ").append(majorTime);

        System.out.println(stringBuilder);
    }
}
