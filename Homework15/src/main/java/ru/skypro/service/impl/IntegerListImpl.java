package ru.skypro.service.impl;

import ru.skypro.exceptions.*;
import ru.skypro.service.IntegerList;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private static Integer[] integers;

    private static final int DEFAULT_CAPACITY = 3;

    private int size;

    public IntegerListImpl() {
        integers = new Integer[DEFAULT_CAPACITY];
    }

    public IntegerListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            integers = new Integer[initialCapacity];
        } else {
            throw new IncorrectArraySizeException("Размера массива должен быть больше нуля");
        }
    }

    private static Integer[] grow(Integer[] arr) {
        integers = new Integer[(int) (arr.length * 1.5)];
        System.arraycopy(arr, 0, integers, 0, arr.length);
        return integers;
    }

    private static void quickSort(Integer[] arr) {
        quickSort(arr, 0 , arr.length);
    }

    private static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static boolean binarySearch(Integer[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (size == integers.length) {
            return grow(integers)[size++] = item;
        }
        return integers[size++] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (size == index) {
            return integers[size++] = item;
        }
        if (size == integers.length) {
            System.arraycopy(integers, index, grow(integers), index + 1, size - index);
        }else {
            System.arraycopy(integers, index, integers, index + 1, size - index);
        }
        integers[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        return integers[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент не найден");
        }
        System.arraycopy(integers, index + 1, integers, index, size - index);
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = integers[index];
        System.arraycopy(integers, index + 1, integers, index, size - index);
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        validateItem(item);
        Integer[] copy = toArray();
        if (copy.length > 1) {
            quickSort(copy);
        }
        return binarySearch(copy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException("Некорректный индекс");
        }
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("Значение не может быть null");
        }
    }
}

