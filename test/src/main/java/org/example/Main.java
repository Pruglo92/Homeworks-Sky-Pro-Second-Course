package org.example;

public class Main {
    public static void main(String[] args) {
        printFibonacci();
    }
    public static void printNumsWithRecursion(int currentNum) {
        System.out.println(currentNum);
            printNumsWithRecursion(currentNum + 10);
    }

    public static void printFibonacci() {
        int a = 0;
        int b = 1;

        for (int i = 0; i < 5; i++) {
            System.out.print(a + " " + b + " ");
            a += b;
            b += a;
        }
        System.out.println();
    }
}