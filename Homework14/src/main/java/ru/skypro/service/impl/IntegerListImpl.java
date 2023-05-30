package ru.skypro.service.impl;

import ru.skypro.exceptions.*;
import ru.skypro.service.IntegerList;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] integers;

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    public IntegerListImpl() {
        integers = new Integer[DEFAULT_CAPACITY];
    }

    public IntegerListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.integers = new Integer[initialCapacity];
        } else {
            throw new IncorrectArraySizeException("Размера массива должен быть больше нуля");
        }
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
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
        validateSize();
        validateItem(item);
        return integers[size++] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateIndex(index);
        validateItem(item);
        if (size == index) {
            return integers[size++] = item;
        }
        System.arraycopy(integers, index, integers, index + 1, size - index);
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
    public boolean contains(Integer[] arr,Integer item) {
        sortInsertion(arr);
        return binarySearch(arr, item);
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

    private void validateSize() {
        if (size == integers.length) {
            throw new ArrayIsFullException("Массив полностью заполнен");
        }
    }
}

