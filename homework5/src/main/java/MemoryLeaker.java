import java.util.ArrayList;
import java.util.List;

public class MemoryLeaker {
    static long startTime;
    void run(int size) {
        startTime = System.currentTimeMillis();
        System.out.println("MemoryLeaker start");
        int listHalfSize = size/2;
        List<Integer> list = new ArrayList<Integer>(size);
    while (true){
        for (int i = 0; i < size; i++) {
            list.add(12345);
        }
        list.subList(listHalfSize,size).clear();
        System.out.println("New elements add: " + size);
        System.out.println("Elements removed: " + listHalfSize);
        System.out.println("List size: " + list.size());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GC.printGCstatic();
        System.out.println("Time from start: " + (System.currentTimeMillis()-startTime)/1000 + " seconds");
    }
    }
}
