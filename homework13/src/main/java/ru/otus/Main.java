package ru.otus;

public class Main {
    static int[] numbers;

    public static void main(String[] args) {
        numbers= RandomArray.randomArray(20);
        int[] result = Sorter.parallelSort(numbers);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
