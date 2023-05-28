package ru.skypro;

import ru.skypro.service.impl.StringListImpl;


public class Main {
    public static void main(String[] args) {
        StringListImpl stringList = new StringListImpl();
        System.out.println(stringList.add("1"));
        System.out.println(stringList.add("2"));
        stringList.clear();
        System.out.println(stringList.add("3"));
        System.out.println(stringList.add("4"));
        System.out.println(stringList.add("5"));
        System.out.println(stringList.add("6"));
        stringList.remove(5);
    }
}