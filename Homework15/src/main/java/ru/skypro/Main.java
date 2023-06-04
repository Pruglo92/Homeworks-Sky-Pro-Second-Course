package ru.skypro;

import ru.skypro.service.IntegerList;
import ru.skypro.service.impl.IntegerListImpl;
import ru.skypro.utility.SortComparison;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
//        integerList.add(5);
//        integerList.add(2);
//        integerList.add(7);
//        integerList.add(1,4);
//        integerList.add(1,5);
//        integerList.add(1,6);
//        integerList.add(1,7);
//        integerList.add(1,8);
//        integerList.add(1,9);
//        integerList.add(1,10);
//        integerList.add(1,11);
//        integerList.add(1,12);
//        integerList.add(1,13);
//        integerList.add(1,14);
//        integerList.add(1,15);
//        integerList.add(4);
//        integerList.add(3);
//        integerList.add(8);
//        integerList.add(6);
//        integerList.add(1);
//        integerList.add(9);
//        integerList.add(13);
//        integerList.add(16);
//        integerList.add(12);
//        integerList.add(14);
//        integerList.add(15);
//        integerList.add(11);
//        integerList.add(10);
//        integerList.getSort();
        Random random = new Random();
        int[] randomArr = new int[100_000];

        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = random.nextInt(9) + 1;
        }

        int[] copy1 = Arrays.copyOf(randomArr, 100_000);
        int[] copy2 = Arrays.copyOf(randomArr, 100_000);

//        long start = System.currentTimeMillis();
//        Arrays.sort(randomArr);
//        System.out.println(System.currentTimeMillis() - start);

//        long start1 = System.currentTimeMillis();
//        SortComparison.quickSort(randomArr, 0, randomArr.length - 1);
//        System.out.println(System.currentTimeMillis() - start1);
//        long start2 = System.currentTimeMillis();
//        SortComparison.quickSort(copy1, 0, copy1.length - 1);
//        System.out.println(System.currentTimeMillis() - start2);
//        long start3 = System.currentTimeMillis();
//        SortComparison.quickSort(copy2, 0, copy2.length - 1);
//        System.out.println(System.currentTimeMillis() - start3);

//        long start1 = System.currentTimeMillis();
//        SortComparison.sortBubble(randomArr);
//        System.out.println(System.currentTimeMillis() - start1);
//        long start2 = System.currentTimeMillis();
//        SortComparison.sortBubble(copy1);
//        System.out.println(System.currentTimeMillis() - start2);
//        long start3 = System.currentTimeMillis();
//        SortComparison.sortBubble(copy2);
//        System.out.println(System.currentTimeMillis() - start3);

//        long start4 = System.currentTimeMillis();
//        SortComparison.sortSelection(randomArr);
//        System.out.println(System.currentTimeMillis() - start4);
//        long start5 = System.currentTimeMillis();
//        SortComparison.sortSelection(copy1);
//        System.out.println(System.currentTimeMillis() - start5);
//        long start6 = System.currentTimeMillis();
//        SortComparison.sortSelection(copy2);
//        System.out.println(System.currentTimeMillis() - start6);

//        long start7 = System.currentTimeMillis();
//        SortComparison.sortInsertion(randomArr);
//        System.out.println(System.currentTimeMillis() - start7);
//        long start8 = System.currentTimeMillis();
//        SortComparison.sortInsertion(copy1);
//        System.out.println(System.currentTimeMillis() - start8);
//        long start9 = System.currentTimeMillis();
//        SortComparison.sortInsertion(copy2);
//        System.out.println(System.currentTimeMillis() - start9);
    }
}