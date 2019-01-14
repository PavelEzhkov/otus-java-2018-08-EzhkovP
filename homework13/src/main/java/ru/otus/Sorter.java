package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorter {
    private static int numberOfThreads = 4;
    public static int[] parallelSort(int[] numbers){
        int partSize = numbers.length/ numberOfThreads;
        int partStart = 0;

        List<int[]> parts = new ArrayList<>();
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            int partEnd = ((i== numberOfThreads -1)? numbers.length:(partStart+partSize));
            int[] part = Arrays.copyOfRange(numbers,partStart,partEnd);
            parts.add(part);
            threads[i] = new Thread(new SortingTask(part));
            threads[i].start();
            partStart += partSize;
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int[] result = merge(parts.get(0), parts.get(1));

        for (int i = 2; i < numberOfThreads ; i++) {
            result = merge(result, parts.get(i));
        }

        return result;
    }

    private static int[] merge(int[] a1, int[] a2) {
        int[] out = new int[a1.length+a2.length];
        int a = 0;
        int b = 0;

        for (int i = 0; i < out.length; i++) {
            if (b< a2.length && a<a1.length){
                if (a1[a]>a2[b]) out[i] = a2[b++];
                else out[i] = a1[a++];
            } else if (b<a2.length)
                out[i] = a2[b++];
            else out[i] = a1[a++];
        }
        return out;
    }


    private static class SortingTask implements Runnable {
        private int[] part;
        public SortingTask(int[] part) {
            this.part = part;
        }

        @Override
        public void run() {
            Arrays.sort(part);

        }
    }
}
