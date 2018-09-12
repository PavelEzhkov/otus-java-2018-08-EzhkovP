import java.util.function.Supplier;

public class Memory {
    public  <T> Object printObjectSize(Supplier<T> supplier, int size)
    {final Object[] objects = new Object[size];
    System.gc();
    Runtime runtime = Runtime.getRuntime();
    long memoryStart = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            objects[i] = supplier.get();
        }
     long memoryFinish = runtime.totalMemory() - runtime.freeMemory();
     System.gc();
     System.out.println(supplier.get().getClass().getSimpleName() + " - " + (memoryFinish-memoryStart)/size);
     return objects;

    }
}

