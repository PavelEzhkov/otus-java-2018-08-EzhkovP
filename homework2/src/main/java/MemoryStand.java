import java.util.ArrayList;
import java.util.function.Supplier;

public class MemoryStand {
    private static final int BIGINT = 1000000;

    public static void main(String[] args) {
        Memory memory = new Memory();
        memory.printObjectSize(() -> new String(),BIGINT);
        memory.printObjectSize(() -> new String(new char[]{}), BIGINT);
        memory.printObjectSize(() -> new String[]{}, BIGINT);
        memory.printObjectSize(() -> new boolean[]{}, BIGINT);
        memory.printObjectSize(() -> new int[0], BIGINT);
        memory.printObjectSize(() -> new int[3], BIGINT);
        memory.printObjectSize(() -> new int[4], BIGINT);
        memory.printObjectSize(() -> new Object(), BIGINT);
        memory.printObjectSize((Supplier<ArrayList>) ArrayList::new, BIGINT);
    }
}
