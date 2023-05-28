package ru.skypro.service.impl;

import ru.skypro.exceptions.*;
import ru.skypro.service.StringList;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] strings;

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    public StringListImpl() {
        strings = new String[DEFAULT_CAPACITY];
    }

    public StringListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.strings = new String[initialCapacity];
        } else {
            throw new IncorrectArraySizeException("Размера массива должен быть больше нуля");
        }
    }

    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        return strings[size++] = item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateIndex(index);
        validateItem(item);
        if (size == index) {
            return strings[size++] = item;
        }
        System.arraycopy(strings, index, strings, index + 1, size - index);
        strings[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        return strings[index] = item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент не найден");
        }
        System.arraycopy(strings, index + 1, strings, index, size - index);
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = strings[index];
        System.arraycopy(strings, index + 1, strings, index, size - index);
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return strings[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(strings, size);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException("Некорректный индекс");
        }
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException("Значение не может быть null");
        }
    }

    private void validateSize() {
        if (size == strings.length) {
            throw new ArrayIsFullException("Массив полностью заполнен");
        }
    }
}

