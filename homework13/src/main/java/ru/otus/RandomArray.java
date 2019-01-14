package ru.otus;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomArray {
    public static int[] randomArray(int size){
        Random random = new Random();
        IntStream stream = random.ints(size);
        return stream.toArray();

    }
}
