package ru.skypro.homework10.service;

import java.util.List;

public interface OrderService {
    String addOrderInBucket(Integer id);
    List<Integer> getOrdersFromBucket();
}
