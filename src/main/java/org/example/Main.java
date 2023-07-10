package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

//        bubbleSort(generateArrayCopy()); //6900

//        sortSelection(generateArrayCopy()); //4800

        sortInsertion(generateArrayCopy()); // 2381
        System.out.println(System.currentTimeMillis() - start);

    }

    public static int[] generateArrayCopy() {
        int[] randomArr = IntStream.generate(() -> new Random().nextInt(1000))
                .limit(10000).toArray();
        return Arrays.copyOf(randomArr, 100000);
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minElementId = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementId]) {
                    minElementId = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minElementId];
            arr[minElementId] = arr[i];
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }
}